package org.camrent.routes.api.customer.post

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.CustomerPostImage() {
    post("customers/image") {
        val multipart = call.receiveMultipart()
        val uploadPath = "src/main/resources/images/account/customers/usr_2/profileImage/"

        var index = 1

        multipart.forEachPart { part ->
            if (part is PartData.FileItem) {
                val originalFileName = part.originalFileName ?: "unknown"
                val fileExtension = originalFileName.substringAfterLast('.', "unknown")
                val fileName = "img_${index++}.$fileExtension"

                val fileBytes = part.streamProvider().readBytes()

                val filePath = "$uploadPath$fileName"
                File(filePath).writeBytes(fileBytes)

                part.dispose()
            }
        }

        call.respond(HttpStatusCode.OK, "Received files successfully.")
    }
}
