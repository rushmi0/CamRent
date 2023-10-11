package org.camrent.routes.api.products.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.ProductsService


fun Route.ProductGet() {

    get("product") {

        val products = ProductsService.selectAllFromProducts()
        println("> Host: ${call.request.headers["Host"]}")
        call.respond(HttpStatusCode.OK, products)

    }

}