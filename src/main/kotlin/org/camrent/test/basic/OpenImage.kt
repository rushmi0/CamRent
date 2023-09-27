package org.camrent.test.basic

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.openImage() {

    get ("/image/open") {
        val file = File("src/main/kotlin/org/camrent/database/image/rabbits/rabbit1.jpg")

        // ตรวจสอบว่าไฟล์มีอยู่จริงหรือไม่
        if (file.exists()) {
            call.respondFile(file)
        } else {
            // หากไม่พบไฟล์ ให้ส่ง HTTP 404 Not Found
            call.respond(HttpStatusCode.NotFound)
        }
    }

}
