package mx.edu.cetys.garay.andrea.application.products

data class SaveProductCommandResponse(
    val id: Int,
    val name: String,
    val description: String
)