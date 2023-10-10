package org.camrent.routes.api.products

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.camrent.routes.api.products.post.ProductPost


fun Application.productsRoute() {

    routing {

        route("api/v1") {

            // `POST` "/product" : ใช้สำหรับ เพิ่มข้อมูลสินค่า
            ProductPost()

        }

    }

}