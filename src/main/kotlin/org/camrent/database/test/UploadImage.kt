package org.camrent.database.test

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File


fun Route.uploadImage() {
    post("image/upload") {
        val multipart = call.receiveMultipart()
        var imageFile: File? = null
        var belief: String? = null
        var surname: String? = null

        multipart.forEachPart { part ->
            when (part) {
                is PartData.FileItem -> {
                    imageFile = saveImage(part)
                }
                is PartData.FormItem -> {
                    when (part.name) {
                        "belief" -> belief = part.value
                        "surname" -> surname = part.value
                    }
                }
                is PartData.BinaryChannelItem, is PartData.BinaryItem -> TODO()
            }
        }

        if (imageFile != null) {
            call.respond(HttpStatusCode.OK, "File uploaded successfully")
            println("Received image: ${imageFile?.absolutePath}")
            println("Received belief: $belief")
            println("Received surname: $surname")
        } else {
            call.respond(HttpStatusCode.BadRequest, "No file uploaded")
        }
    }
}

private fun saveImage(imagePart: PartData.FileItem): File {
    val imageFile = File("src/main/kotlin/org/camrent/database/image/camera/${imagePart.originalFileName}")
    imagePart.streamProvider().use { input ->
        imageFile.outputStream().buffered().use { output ->
            input.copyTo(output)
        }
    }
    return imageFile
}
