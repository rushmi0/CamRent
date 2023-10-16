package org.camrent.routes.api.products.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.field.ProductsField
import org.camrent.database.service.ProductsService
import org.camrent.utils.ShiftTo.buildUrl
import org.camrent.utils.ShiftTo.getImageFileName
import java.io.File

fun Route.ProductGetProductByID() {

    get("product/id/{id}") {
        try {

            // ดึงค่า parameter id จาก URL และแปลงเป็น Int
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

            // ค้นหาสินค้าด้วย id ในฐานข้อมูล
            val product = ProductsService.findProductsByID(id)
                ?: throw NotFoundException("ไม่พบสินค้าสำหรับ `ID`: $id")

            // กำหนด URL หลักของ API
            val BASE_URL = "http://127.0.0.1:8080/api/v1/"

            // ดึงชื่อไฟล์รูปภาพของสินค้า
            val img1 = getImageFileName(product.image1)
            val img2 = getImageFileName(product.image2)
            val img3 = getImageFileName(product.image3)
            val img4 = getImageFileName(product.image4)

            // สร้าง object ของ ProductsField โดยแทนที่ชื่อไฟล์รูปภาพในข้อมูลสำหรับ response ด้วยค่า "N/A" ถ้าชื่อไฟล์เป็น "N/A"
            val dataInfo = ProductsField(
                productID = product.productID,
                productName = product.productName,
                // แทนที่ชื่อไฟล์รูปภาพในข้อมูลสำหรับ response ด้วยค่า "N/A" ถ้าชื่อไฟล์เป็น "N/A"
                image1 = if (img1 != "N/A") "${BASE_URL}product/id/$id/idx/img1/${img1.buildUrl()}" else "N/A",
                image2 = if (img2 != "N/A") "${BASE_URL}product/id/$id/idx/img2/${img2.buildUrl()}" else "N/A",
                image3 = if (img3 != "N/A") "${BASE_URL}product/id/$id/idx/img3/${img3.buildUrl()}" else "N/A",
                image4 = if (img4 != "N/A") "${BASE_URL}product/id/$id/idx/img4/${img4.buildUrl()}" else "N/A",
                type = product.type,
                price = product.price,
                specDetail = product.specDetail,
                description = product.description,
                status = product.status,
                storeID = product.storeID
            )

            // ตอบกลับด้วยข้อมูลที่ได้สร้าง
            call.respond(HttpStatusCode.OK, dataInfo)

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
                "รูปแบบข้อมูลไม่ถูกต้อง กรุณาตรวจสอบข้อมูลและลองใหม่อีกครั้ง ${e.message}"
            )
        } catch (e: Exception) {
            // ดักจับข้อผิดพลาดทั่วไป
            call.respond(
                HttpStatusCode.InternalServerError,
                "เกิดข้อผิดพลาดขณะประมวลผลคำขอของคุณ กรุณาลองใหม่อีกครั้ง ${e.message}"
            )
        }
    }



}