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


fun Route.ProductGet() {

    get("products") {

        try {

            // ดึงข้อมูลสินค้าทั้งหมดจากฐานข้อมูล
            val products = ProductsService.selectAllFromProducts()

            // กำหนด URL หลักของ API
            val BASE_URL = "http://127.0.0.1:8080/api/v1/"

            // แสดง Host ใน request headers
            println("> Host: ${call.request.headers["Host"]}")

            // สร้าง list ของข้อมูลสินค้าที่จะส่งใน response
            val dataInfoList = products.mapIndexed { _, product ->

                // ดึงชื่อไฟล์รูปภาพของสินค้า
                val img1 = getImageFileName(product.img1)
                val img2 = getImageFileName(product.img2)
                val img3 = getImageFileName(product.img3)
                val img4 = getImageFileName(product.img4)

                // สร้าง object ของ ProductsField โดยแทนที่ชื่อไฟล์รูปภาพในข้อมูลสำหรับ response ด้วยค่า "N/A" ถ้าชื่อไฟล์เป็น "N/A"
                ProductsField(
                    id = product.id,
                    name = product.name,
                    // แทนที่ชื่อไฟล์รูปภาพในข้อมูลสำหรับ response ด้วยค่า "N/A" ถ้าชื่อไฟล์เป็น "N/A"
                    img1 = if (img1 != "N/A") "${BASE_URL}product/id/${product.id}/idx/img1/${img1.buildUrl()}" else "N/A",
                    img2 = if (img2 != "N/A") "${BASE_URL}product/id/${product.id}/idx/img2/${img2.buildUrl()}" else "N/A",
                    img3 = if (img3 != "N/A") "${BASE_URL}product/id/${product.id}/idx/img3/${img3.buildUrl()}" else "N/A",
                    img4 = if (img4 != "N/A") "${BASE_URL}product/id/${product.id}/idx/img4/${img4.buildUrl()}" else "N/A",
                    type = product.type,
                    price = product.price,
                    specDetail = product.specDetail,
                    description = product.description,
                    status = product.status,
                    storeID = product.storeID
                )
            }

            // ตอบกลับด้วยข้อมูลที่ได้สร้าง
            call.respond(HttpStatusCode.OK, dataInfoList)

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