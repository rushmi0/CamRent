package org.camrent.database.test

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.io.File

fun Route.uploadFile() {

// ฟังก์ชัน `uploadFile()` รับ POST request บนเส้นทาง `/file`
    post("/file") {

        // รับไฟล์ที่อัปโหลดโดยใช้ `call.receiveMultipart()`
        val multipart = call.receiveMultipart()

        // วนซ้ำผ่านไฟล์ที่อัปโหลดทั้งหมด
        multipart.forEachPart { part ->

            // ตรวจสอบว่าไฟล์เป็นไฟล์รูปภาพหรือไม่
            when (part) {

                // ไฟล์ที่ไม่ใช่รูปภาพจะถูกข้ามไป
                is PartData.FormItem -> Unit

                // ไฟล์รูปภาพจะถูกบันทึกลงดิสก์
                is PartData.FileItem -> {

                    // ตรวจสอบชื่อไฟล์
                    if (part.name == "image") {

                        // สร้างไฟล์ใหม่
                        val file = File("src/main/kotlin/org/camrent/database/image/camera/", "myImage.jpg")

                        // คัดลอกเนื้อหาไฟล์ลงดิสก์
                        part.streamProvider().use { input ->
                            file.outputStream().buffered().use { output ->
                                input.copyTo(output)
                            }
                        }
                    } else if (part.headers["Content-Type"]?.contains("application/json") == true) {
                        // อ่านเนื้อหาของไฟล์ JSON
                        val reader = part.streamProvider().reader()
                        val data = call.receive<Data>()

                        // ทำบางอย่างกับข้อมูล JSON
                        println(data)
                    }
                }

                // ไฟล์อื่นๆ จะถูกข้ามไป
                else -> Unit
            }

            // ตอบกลับ HTTP 200 OK
            call.respond(HttpStatusCode.OK)
        }
    }


}

