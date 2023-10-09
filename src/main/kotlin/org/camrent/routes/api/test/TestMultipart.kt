package org.camrent.routes.api.test

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.netty.handler.codec.http.multipart.DiskAttribute
import java.io.File

fun Route.TestMultipart() {

    post("upload") {

        // Retrieve the "Stores" header value
        val storesHeaderValue = call.request.headers["Stores"]
        println(storesHeaderValue)

        // Check if the header value is "Stores"
        if (storesHeaderValue == "Stores") {
            val multipart = call.receiveMultipart()
            multipart.forEachPart { part ->
                if (part is io.ktor.http.content.PartData.FileItem) {
                    // Save the file to the appropriate directory
                    val fileName = part.originalFileName ?: "image.jpg"
                    val filePath = "${DiskAttribute.baseDirectory}/$fileName"
                    val file = File(filePath)
                    part.streamProvider().use { input ->
                        file.outputStream().buffered().use { output ->
                            input.copyTo(output)
                        }
                    }
                }
                part.dispose()
            }
            call.respond(HttpStatusCode.OK, "Image uploaded successfully.")
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid header value.")
        }


    }

}