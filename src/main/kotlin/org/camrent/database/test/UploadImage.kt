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
        try {
            val multipart = call.receiveMultipart()  // รับข้อมูลจาก multipart request

            // ดึงค่า Witness และ Signature จาก Headers
            val signature = call.request.headers["Signature"]
            val witness = call.request.headers["Witness"]

            var imageFile: File? = null

            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FileItem -> {
                        imageFile = saveImage(part)  // บันทึกไฟล์ภาพลงในระบบ
                    }

                    is PartData.BinaryChannelItem, is PartData.BinaryItem -> {
                        call.respond(HttpStatusCode.UnsupportedMediaType, "ไม่รองรับข้อมูล Binary")
                    }

                    is PartData.FormItem -> TODO()
                }
            }

            if (imageFile != null) {
                call.respond(HttpStatusCode.OK, "อัปโหลดไฟล์เรียบร้อยแล้ว")
                println("ได้รับภาพ: ${imageFile?.absolutePath}")  // พิมพ์ที่อยู่ของไฟล์ภาพที่ถูกอัปโหลด
            } else {
                call.respond(HttpStatusCode.BadRequest, "ไม่มีการอัปโหลดไฟล์")  // ตอบกลับว่าไม่มีไฟล์ถูกอัปโหลด
            }


        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                "เกิดข้อผิดพลาด: ${e.message}"
            )  // ตอบกลับว่าเกิดข้อผิดพลาด
        }
    }
}

private fun saveImage(imagePart: PartData.FileItem): File {
    // สร้างไฟล์ภาพใหม่ตามชื่อเดิม
    val imageFile = File("src/main/resources/images/account/customers/usr_1/profileImage/${imagePart.originalFileName}")
    // ใช้ข้อมูลจาก stream provider เพื่อบันทึกไฟล์
    imagePart.streamProvider().use { input ->
        imageFile.outputStream().buffered().use { output ->
            input.copyTo(output)
        }
    }
    // ส่งคืนไฟล์ภาพที่ถูกบันทึก
    return imageFile
}
