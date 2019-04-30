package mx.edu.cetys.garay.andrea.impl

import mx.edu.cetys.garay.andrea.application.RequestHandler
import mx.edu.cetys.garay.andrea.application.products.GetProductQuery
import mx.edu.cetys.garay.andrea.application.products.GetProductQueryResponse
import mx.edu.cetys.garay.andrea.application.products.SaveProductCommand
import mx.edu.cetys.garay.andrea.application.products.SaveProductCommandResponse
import mx.edu.cetys.garay.andrea.application.sales.GetSaleQuery
import mx.edu.cetys.garay.andrea.application.sales.GetSaleQueryResponse
import mx.edu.cetys.garay.andrea.dto.ProductDTO
import mx.edu.cetys.garay.andrea.dto.SaleDTO


class ProductApiImpl(
    private val saveProductCommandHandler:
    RequestHandler<SaveProductCommand, SaveProductCommandResponse>,
    private val getProductQueryHandler: RequestHandler<GetProductQuery, GetProductQueryResponse>,
    private val getSaleQueryHandler: RequestHandler<GetSaleQuery, GetSaleQueryResponse>
) {
    fun addProduct(request: AddProductRequest): AddProductResponse {
        val response = saveProductCommandHandler.handle(SaveProductCommand(request.name, request.description))
        return AddProductResponse(response.id, response.name, response.description)
    }

    fun getProduct(request: GetProductRequest): GetProductResponse {
        val response = getProductQueryHandler.handle(GetProductQuery(request.id))
        return GetProductResponse(response.products)
    }

    fun getSale(request: GetSaleRequest): GetSaleResponse {
        val response = getSaleQueryHandler.handle(GetSaleQuery(request.id))
        return GetSaleResponse(response.sale)
    }
}

data class AddProductRequest(
    val name: String,
    val description: String
)

data class AddProductResponse(
    val id: Int,
    val name: String,
    val description: String
)

data class GetProductRequest(
    val id: Int?
)

data class GetProductResponse(
    val products: ProductDTO
)

data class GetSaleRequest(
    val id: Int?
)

data class GetSaleResponse(
    val sales: SaleDTO
)
