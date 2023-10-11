package org.camrent.routes.api.products.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.field.ProductsField
import org.camrent.database.service.ProductsService
import org.camrent.utils.ShiftTo.buildUrl
import java.io.File

fun Route.ProductGetProductByID() {
    get("product/id/{id}/idx/{index}/{image}") {
        // ดึงค่า parameter index จาก URL
        val index = call.parameters["index"] ?: throw IllegalArgumentException("ไม่ได้ระบุค่า index")
        // ดึงค่า parameter id จาก URL และตรวจสอบว่าไม่เป็น null
        val id = call.parameters["id"]
            ?: throw IllegalArgumentException("ไม่ได้ระบุค่า id")

        try {
            // ค้นหาสินค้าด้วย id ในฐานข้อมูล
            val product = ProductsService.findProductsByProductsID(id.toInt())
                ?: throw NotFoundException("ไม่พบสินค้าสำหรับ `ID`: $id")

            val imagePath = when (index) {
                "img1" -> product.image1
                "img2" -> product.image2
                "img3" -> product.image3
                "img4" -> product.image4
                else -> null
            }

            if (imagePath != null) {
                // สร้างไฟล์จากที่อยู่รูปภาพ
                val file = File(imagePath)
                call.respondFile(file)  // ตอบกลับด้วยไฟล์ภาพ
                println(imagePath)  // แสดงที่อยู่ของไฟล์ภาพ
            } else {
                call.respond(HttpStatusCode.NotFound, "ยังไม่มีรูป")
            }

        } catch (e: IllegalArgumentException) {
            // ดักจับข้อผิดพลาดที่เกี่ยวกับข้อมูลไม่ถูกต้อง
            call.respond(HttpStatusCode.BadRequest, e.message ?: "ข้อมูลไม่ถูกต้อง")
        } catch (e: Exception) {
            // ดักจับข้อผิดพลาดทั่วไป
            call.respond(HttpStatusCode.InternalServerError, "เกิดข้อผิดพลาด: ${e.message}")
        }
    }



    get("product/id/{id}") {
        try {
            // ดึงค่า parameter id จาก URL และแปลงเป็น Int
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

            // ค้นหาสินค้าด้วย id ในฐานข้อมูล
            val product = ProductsService.findProductsByProductsID(id)
                ?: throw NotFoundException("ไม่พบสินค้าสำหรับ `ID`: $id")

            val BASE_URL = "http://127.0.0.1:8080/api/v1/"

            // ดึงชื่อไฟล์รูปภาพจากที่อยู่ของรูป
            val img1 = File(product.image1).name
            val img2 = File(product.image2).name
            val img3 = File(product.image3).name
            val img4 = File(product.image4).name

            // สร้างข้อมูลสำหรับ response
            val dataInfo = ProductsField(
                productID = product.productID,
                productName = product.productName,
                image1 = "${BASE_URL}product/id/$id/idx/img1/${img1.buildUrl()}",
                image2 = "${BASE_URL}product/id/$id/idx/img2/${img2.buildUrl()}",
                image3 = "${BASE_URL}product/id/$id/idx/img3/${img3.buildUrl()}",
                image4 = "${BASE_URL}product/id/$id/idx/img4/${img4.buildUrl()}",
                type = product.type,
                price = product.price,
                specDetail = product.specDetail,
                description = product.description,
                status = product.status,
                storeID = product.storeID
            )

            // ตอบกลับด้วยข้อมูลที่ได้สร้าง
            call.respond(HttpStatusCode.OK, dataInfo)

        } catch (e: IllegalArgumentException) {
            // ดักจับข้อผิดพลาดที่เกี่ยวกับข้อมูลไม่ถูกต้อง
            call.respond(HttpStatusCode.BadRequest, e.message ?: "ข้อมูลไม่ถูกต้อง")
        } catch (e: ContentTransformationException) {
            // ดักจับข้อผิดพลาดที่เกี่ยวกับการแปลงข้อมูล
            call.respond(HttpStatusCode.BadRequest, "รูปแบบข้อมูลไม่ถูกต้อง")
        } catch (e: Exception) {
            // ดักจับข้อผิดพลาดทั่วไป
            call.respond(HttpStatusCode.InternalServerError, "เกิดข้อผิดพลาดขณะประมวลผลคำขอของคุณ")
        }
    }


}
