package org.camrent.routes.api.customer.test

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.UploadFileTest() {

    post("image/upload") {
        val multipart = call.receiveMultipart()
        val filePart = multipart.readAllParts().filterIsInstance<PartData.FileItem>().singleOrNull()

        if (filePart != null) {
            // สร้างชื่อไฟล์ใหม่ตามที่กำหนด
            val newFileName = "new_image.jpg"

            val file = File("src/main/kotlin/org/camrent/database/image/camera/$newFileName")
            filePart.streamProvider().use { input ->
                file.outputStream().buffered().use { output ->
                    input.copyTo(output)
                }
            }
            call.respond(HttpStatusCode.OK, "File uploaded and renamed successfully")
        } else {
            call.respond(HttpStatusCode.BadRequest, "No file uploaded")
        }
    }

}