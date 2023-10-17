package org.camrent.routes.api.test

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.record.ProductPreview
import java.io.File

fun Route.PreviewImage2() {

    get("preview/img/1") {

        val img1Path = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/1.jpg"
        val img2Path = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/2.jpg"
        val img3Path = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/3.jpg"
        val img4Path = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/4.jpg"


        val response = ProductPreview(
            productName = "รายการรูปทั้งหมด",
            img1 = "http://127.0.0.1:8080/api/v1/preview/img/1/preview/1.jpg",
            img2 = "http://127.0.0.1:8080/api/v1/preview/img/1/preview/2.jpg",
            img3 = "http://127.0.0.1:8080/api/v1/preview/img/1/preview/3.jpg",
            img4 = "http://127.0.0.1:8080/api/v1/preview/img/1/preview/4.jpg"
        )

        call.respond(response)
    }


    get("preview/img/1/preview/{fileName}") {
        val fileName = call.parameters["fileName"] ?: return@get call.respond(HttpStatusCode.BadRequest)
        val filePath = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/$fileName"
        val file = File(filePath)

        if (file.exists()) {
            call.respondFile(file)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

}

fun encodeImageUrl(baseUrl: String, fileName: String): String {
    val encodedFileName = java.net.URLEncoder.encode(fileName, "UTF-8")
    return "$baseUrl/$encodedFileName"
}


