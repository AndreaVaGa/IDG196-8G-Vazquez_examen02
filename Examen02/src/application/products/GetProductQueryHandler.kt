package mx.edu.cetys.garay.andrea.application.products


import mx.edu.cetys.garay.andrea.application.RequestHandler
import mx.edu.cetys.garay.andrea.exposed.CetysKartFacade


class GetProductQueryHandler(private val ckf: CetysKartFacade) :
    RequestHandler<GetProductQuery, GetProductQueryResponse> {
    override fun handle(message: GetProductQuery): GetProductQueryResponse {
        val response = ckf.getProduct(message.id)
        return GetProductQueryResponse(response)
    }
}