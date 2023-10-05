package org.camrent.routes.api.customer.patch

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.CustomerService
import org.camrent.security.securekey.ECDSA
import org.camrent.security.securekey.EllipticCurve.getDecompress
import org.camrent.security.securekey.Sha256
import org.camrent.utils.AccountDirectory.deleteFilesInPathAndCheckExistence
import org.camrent.utils.AccountDirectory.saveImage
import org.camrent.utils.ShiftTo.B32decode
import org.camrent.utils.ShiftTo.ByteArrayToBigInteger
import java.io.File

fun Route.CustomerUploadImage() {

    post("customers/img/id/{id}") {

        try {
            // ดึงค่า id จากพารามิเตอร์และแปลงเป็น Int ถ้าเป็นไปได้
            val id =
                call.parameters["id"]?.toIntOrNull() ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

            // ค้นหาข้อมูลลูกค้าด้วย ID
            val customerData = CustomerService.findCustomerByUserID(id) ?: throw NotFoundException("ไม่พบลูกค้าสำหรับ `ID`: $id")


            // รับข้อมูลจาก multipart request
            val multipart = call.receiveMultipart()

            var imageFile: File? = null

            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FileItem -> {
                        // บันทึกไฟล์ภาพลงในระบบ
                        imageFile = saveImage(id, "customers", "profileImage", part)
                    }

                    is PartData.BinaryChannelItem, is PartData.BinaryItem -> {
                        // ไม่รองรับข้อมูล Binary
                        call.respond(HttpStatusCode.UnsupportedMediaType, "ไม่รองรับข้อมูล Binary")
                        return@forEachPart
                    }

                    is PartData.FormItem -> TODO()
                }
            }

            if (imageFile != null) {

                val profile = customerData.profileImage!!

                if (profile == "N/A") {
                    val profileUpdate = CustomerService.update(
                        customerData.customerID!!,
                        "ProfileImage",
                        "${imageFile?.absolutePath}" // ครอบ double code symbol เพื่อแปลงข้อมูลเป็น String
                    )
                    // ตอบกลับว่าอัปโหลดไฟล์เรียบร้อย
                    call.respond(HttpStatusCode.OK)
                    println("ได้รับภาพ: $profileUpdate")
                } else {

                    val success = deleteFilesInPathAndCheckExistence(profile)
                    if (success) {
                        val profileUpdate = CustomerService.update(
                            customerData.customerID!!,
                            "ProfileImage",
                            "${imageFile?.absolutePath}"
                        )
                        // ตอบกลับว่าอัปโหลดไฟล์เรียบร้อย
                        call.respond(HttpStatusCode.OK)
                        println("ได้รับภาพ: $profileUpdate")
                    }

                }

            } else {
                // ตอบกลับว่าไม่มีการอัปโหลดไฟล์
                call.respond(HttpStatusCode.BadRequest, "ไม่มีการอัปโหลดไฟล์")
            }

        } catch (e: IllegalArgumentException) {
            // ตอบกลับว่าเกิดข้อผิดพลาดที่รับข้อมูลเข้ามาไม่ถูกต้อง
            call.respond(
                HttpStatusCode.BadRequest,
                "เกิดข้อผิดพลาด: ${e.message}"
            )
        } catch (e: NotFoundException) {
            // ตอบกลับว่าไม่พบข้อมูลลูกค้าสำหรับ `ID` ที่ระบุ
            call.respond(
                HttpStatusCode.NotFound,
                "เกิดข้อผิดพลาด: ${e.message}"
            )
        } catch (e: Exception) {
            // ตอบกลับว่าเกิดข้อผิดพลาดภายในระบบ
            call.respond(
                HttpStatusCode.InternalServerError,
                "เกิดข้อผิดพลาด: ${e.message}"
            )
        }
    }
}
