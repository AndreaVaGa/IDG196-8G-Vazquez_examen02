package mx.edu.cetys.garay.andrea.exposed

import mx.edu.cetys.garay.andrea.EXPOSED_CONNECTION_STRING
import mx.edu.cetys.garay.andrea.EXPOSED_DRIVER
import mx.edu.cetys.garay.andrea.EXPOSED_PASSWORD
import mx.edu.cetys.garay.andrea.EXPOSED_USER
import mx.edu.cetys.garay.andrea.dto.ProductDTO
import mx.edu.cetys.garay.andrea.dto.SaleDTO
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction


class CetysKartFacadeImpl : CetysKartFacade {
    override fun getProduct(id: Int?): ProductDTO {
        val storedProcedureRawSQL = "exec cetyskart.get_product '$id'"
        var product = ProductDTO(0, "", "")

        Database.connect(
            EXPOSED_CONNECTION_STRING,
            EXPOSED_DRIVER,
            EXPOSED_USER,
            EXPOSED_PASSWORD
        )

        transaction {
            execSp(storedProcedureRawSQL) {
                if (it.next()) {
                    val statusCode = it.getInt("StatusCode")
                    when (statusCode) {
                        500 -> throw Exception("FAIL")
                    }
                    product = ProductDTO(
                        it.getInt("product_id"),
                        it.getString("product_name"),
                        it.getString("product_description")
                    )
                }
            }
        }
        return product
    }

override fun addProduct(name: String, description: String): ProductDTO {
    val storedProcedureRawSQL = "exec cetyskart.add_product '$name','$description'"
    var product = ProductDTO(0, "", "")

    Database.connect(
        EXPOSED_CONNECTION_STRING,
        EXPOSED_DRIVER,
        EXPOSED_USER,
        EXPOSED_PASSWORD
    )

    transaction {
        execSp(storedProcedureRawSQL) {
            if (it.next()) {
                val statusCode = it.getInt("StatusCode")
                when (statusCode) {
                    500 -> throw Exception("FAIL")
                }
                product = ProductDTO(
                    it.getInt("product_id"),
                    it.getString("product_name"),
                    it.getString("product_description")
                )
            }
        }
    }
    return product
}
    override fun getSale(id: Int?): SaleDTO {
        val storedProcedureRawSQL = "exec cetyskart.get_sale '$id'"
        var sale = SaleDTO(0, "", "","")

        Database.connect(
            EXPOSED_CONNECTION_STRING,
            EXPOSED_DRIVER,
            EXPOSED_USER,
            EXPOSED_PASSWORD
        )

        transaction {
            execSp(storedProcedureRawSQL) {
                if (it.next()) {
                    val statusCode = it.getInt("StatusCode")
                    when (statusCode) {
                        500 -> throw Exception("FAIL")
                    }
                    sale = SaleDTO(
                        it.getInt("sale_id"),
                        it.getString("sale_customer"),
                        it.getString("sale_date"),
                        it.getString("sale_shipping_address")
                    )
                }
            }
        }
        return sale
    }
}