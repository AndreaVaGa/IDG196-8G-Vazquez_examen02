package mx.edu.cetys.garay.andrea.application.products

import mx.edu.cetys.garay.andrea.application.Request


data class SaveProductCommand(
    val name: String,
    val description: String
) : Request