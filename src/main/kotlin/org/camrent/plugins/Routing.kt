package org.camrent.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.routes.api.customerRoute
import org.camrent.database.test.uploadFile
import org.camrent.database.test.uploadImage
import org.camrent.test.basic.basicBasePath

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            call.respondText("Hello World!")
        }


        uploadImage()
        uploadFile()
        basicBasePath()
    }
}
