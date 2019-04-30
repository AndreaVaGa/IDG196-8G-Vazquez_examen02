package mx.edu.cetys.garay.andrea

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.features.*
import mx.edu.cetys.garay.andrea.application.products.GetProductQueryHandler
import mx.edu.cetys.garay.andrea.application.products.SaveProductCommandHandler
import mx.edu.cetys.garay.andrea.application.sales.GetSaleQueryHandler
import mx.edu.cetys.garay.andrea.exposed.CetysKartFacadeImpl
import mx.edu.cetys.garay.andrea.impl.AddProductRequest
import mx.edu.cetys.garay.andrea.impl.GetProductRequest
import mx.edu.cetys.garay.andrea.impl.GetSaleRequest
import mx.edu.cetys.garay.andrea.impl.ProductApiImpl

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val ckf = CetysKartFacadeImpl()
    val productApiImpl = ProductApiImpl(
        SaveProductCommandHandler(ckf),
        GetProductQueryHandler(ckf),
        GetSaleQueryHandler (ckf)
    )
    install(Authentication) {
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    routing {
        get("/") {
            call.respondText("Examen segundo Parcial Andrea", contentType = ContentType.Text.Plain)
        }
        route("api/cetyskart/public/v1/products/{id}"){
            get {
                val id : Int? = (call.parameters["id"] ?: "").toInt()
                call.respond(productApiImpl.getProduct(GetProductRequest(id)))
            }
            post {
                val postObject = call.receive<AddProductRequest>()

                call.respond(productApiImpl.addProduct(postObject))
            }


        }
        route("api/cetyskart/public/v1/sales/{id}"){
            get {
                val id : Int? = (call.parameters["id"] ?: "").toInt()
                call.respond(productApiImpl.getSale(GetSaleRequest(id)))
            }


        }


    }
}

