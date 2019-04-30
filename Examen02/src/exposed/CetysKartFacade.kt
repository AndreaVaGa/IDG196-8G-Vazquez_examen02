package mx.edu.cetys.garay.andrea.exposed

import mx.edu.cetys.garay.andrea.dto.ProductDTO
import mx.edu.cetys.garay.andrea.dto.SaleDTO


interface CetysKartFacade {
    fun addProduct(name: String, description: String): ProductDTO
    fun getProduct(id: Int?): ProductDTO
    fun getSale(id: Int?): SaleDTO
}