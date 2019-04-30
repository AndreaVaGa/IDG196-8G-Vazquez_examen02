package mx.edu.cetys.garay.andrea.application.products

import mx.edu.cetys.garay.andrea.application.RequestHandler
import mx.edu.cetys.garay.andrea.exposed.CetysKartFacade


class SaveProductCommandHandler(private val ckf: CetysKartFacade) :
    RequestHandler<SaveProductCommand, SaveProductCommandResponse> {

    override fun handle(message: SaveProductCommand): SaveProductCommandResponse {
        require(message.name.isNotBlank())

        val product = ckf.addProduct(message.name, message.description)
        return SaveProductCommandResponse(product.id, product.name, product.description)
    }
}