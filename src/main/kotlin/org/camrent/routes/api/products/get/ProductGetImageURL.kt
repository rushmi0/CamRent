package org.camrent.routes.api.products.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.ProductsService
import java.io.File

fun Route.ProductGetImageURL() {

    get("product/id/{id}/idx/{index}/{image}") {
        // ดึงค่า parameter index จาก URL
        val index = call.parameters["index"] ?: throw IllegalArgumentException("ไม่ได้ระบุค่า index")
        // ดึงค่า parameter id จาก URL และตรวจสอบว่าไม่เป็น null
        val id = call.parameters["id"] ?: throw IllegalArgumentException("ไม่ได้ระบุค่า id")

        try {
            // ค้นหาสินค้าด้วย id ในฐานข้อมูล
            val product = ProductsService.findProductsByID(id.toInt())
                ?: throw NotFoundException("ไม่พบสินค้าสำหรับ `ID`: $id")

            // ตรวจสอบ index และดึง path ของรูปภาพ
            val imagePath = when (index) {
                "img1" -> product.img1
                "img2" -> product.img2
                "img3" -> product.img3
                "img4" -> product.img4
                else -> null
            }

            // ตรวจสอบว่ามี path ของรูปภาพหรือไม่
            if (imagePath != null) {
                // สร้างไฟล์จากที่อยู่รูปภาพ
                val file = File(imagePath)
                call.respondFile(file)  // ตอบกลับด้วยไฟล์ภาพ
            } else {
                call.respond(
                    HttpStatusCode.NotFound,
                    "ยังไม่มีรูป"
                )
            }


        } catch (e: NotFoundException) {
            // ดักจับข้อผิดพลาดเมื่อไม่พบสินค้า
            call.respond(
                HttpStatusCode.NotFound,
                "ไม่พบสินค้าสำหรับ `ID`: ${e.message}"
            )
        } catch (e: IllegalArgumentException) {
            // ดักจับข้อผิดพลาดที่เกี่ยวกับข้อมูลไม่ถูกต้อง
            call.respond(
                HttpStatusCode.BadRequest,
                e.message ?: "ข้อมูลไม่ถูกต้อง"
            )
        } catch (e: ContentTransformationException) {
            // ดักจับข้อผิดพลาดที่เกี่ยวกับการแปลงข้อมูล
            call.respond(
                HttpStatusCode.BadRequest,
                "ข้อมูลไม่ถูกต้อง: ${e.message}"
            )
        } catch (e: Exception) {
            // ดักจับข้อผิดพลาดทั่วไป
            call.respond(
                HttpStatusCode.InternalServerError,
                "เกิดข้อผิดพลาดในการดึงข้อมูล: ${e.message}"
            )
        }
    }

}