package mx.edu.cetys.garay.andrea.application.sales

import mx.edu.cetys.garay.andrea.application.RequestHandler

import mx.edu.cetys.garay.andrea.exposed.CetysKartFacade

class GetSaleQueryHandler (private val ckf: CetysKartFacade) :
    RequestHandler<GetSaleQuery, GetSaleQueryResponse> {
    override fun handle(message: GetSaleQuery): GetSaleQueryResponse {
        val response = ckf.getSale(message.id)
        return GetSaleQueryResponse(response)
    }
}