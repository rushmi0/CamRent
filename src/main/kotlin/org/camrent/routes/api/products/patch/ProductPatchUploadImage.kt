package org.camrent.routes.api.products.patch

import io.ktor.server.routing.*

fun Route.ProductPatchUploadImage() {

    // use Ktor framework : Create REST API `PATCH` path "/products//img/id/{id}" get image file from client and save to server.
    patch("products/img/id/{id}") {


    }

}