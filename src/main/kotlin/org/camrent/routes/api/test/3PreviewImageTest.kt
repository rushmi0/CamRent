package org.camrent.routes.api.test

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File


data class ImageInfo(val description: String, val url: String)

fun Route.PreviewImage3() {

    get("preview/img/2") {
        val imageFolder = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/"

        val imageUrls = listOf(
            ImageInfo("Image 1", "http://172.0.0.1/api/v1/preview/img/1/preview/1.jpg"),
            ImageInfo("Image 2", "http://172.0.0.1/api/v1/preview/img/1/preview/2.jpg"),
            ImageInfo("Image 3", "http://172.0.0.1/api/v1/preview/img/1/preview/3.jpg"),
            ImageInfo("Image 4", "http://172.0.0.1/api/v1/preview/img/1/preview/4.jpg")
        )

        call.respond(mapOf(
            "des" to "รายการรูปทั้งหมด",
            "images" to imageUrls
        ))
    }

    get("preview/img/2/preview/{image}") {
        val imageName = call.parameters["image"]
        if (imageName != null) {
            val imageFolder = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/"
            val imagePath = imageFolder + imageName

            val file = File(imagePath)
            println(file)
            if (file.exists()) {
                println(file)
                call.respondFile(file)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

}