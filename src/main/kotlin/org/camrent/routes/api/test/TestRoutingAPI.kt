package org.camrent.routes.api.test


import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.TestRoutingAPI() {

    routing {

        route("api/v1") {
            PreviewImage()
            PreviewImage2()
            PreviewImage3()

            ListProductPreview()
        }

    }

}