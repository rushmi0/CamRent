package org.camrent.test.basic

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.downloadFile() {

    get ("/image/download") {
        val fileName = "new_image.jpg" // ชื่อไฟล์ที่คุณต้องการให้ผู้ใช้ดาวน์โหลด

        val file = File("src/main/kotlin/org/camrent/database/image/other/$fileName")
        if (file.exists()) {
            // ตั้งค่า Content-Disposition เพื่อบอกให้เบราว์เซอร์ดาวน์โหลดไฟล์
            call.response.headers.append("Content-Disposition", "attachment; filename=$fileName")
            call.response.header(HttpHeaders.ContentType, ContentType.Image.JPEG.toString())
            call.respondFile(file)
        } else {
            call.respondText("File not found", status = HttpStatusCode.NotFound)
        }
    }

}