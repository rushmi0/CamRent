package org.camrent.routes.api.test

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.ProductsService
import org.camrent.utils.ShiftTo.buildUrl
import java.io.File

data class ImageInfo(val description: String, val url: String)


fun Route.PreviewImage3() {



    get("product/preview/img/{id}") {
        val id = call.parameters["id"]!!

        val image1 = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/1.jpg".substringAfterLast("/")
        val image2 = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/10.jpg".substringAfterLast("/")
        val image3 = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/16.jpg".substringAfterLast("/")
        val image4 = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/11.jpg".substringAfterLast("/")

        val imageUrls = listOf(
            ImageInfo("Image 1", "http://127.0.0.1:8080/api/v1/product/img/$id/index/1/${image1.buildUrl()}"),
            ImageInfo("Image 2", "http://127.0.0.1:8080/api/v1/product/img/$id/index/2/${image2.buildUrl()}"),
            ImageInfo("Image 3", "http://127.0.0.1:8080/api/v1/product/img/$id/index/3/${image3.buildUrl()}"),
            ImageInfo("Image 4", "http://127.0.0.1:8080/api/v1/product/img/$id/index/4/${image4.buildUrl()}")
        )

        val urlList: Map<String, Any> = mapOf(
            "des" to "รายการรูปทั้งหมด", "images" to imageUrls
        )

        call.respond(urlList)
    }

    get("product/img/{id}/index/{index}/{image}") {
        val index = call.parameters["index"]?.toIntOrNull()
        val imageFileName = call.parameters["image"] ?: ""
        val id = call.parameters["id"] ?: ""

        try {
            // ค้นหาที่อยู่ของไฟล์ภาพ โดยการค้นหาที่อยู่ของไฟล์ภาพขึ้นอยู่กับค่า index
            val imagePath = when (index) {
                1 -> ProductsService.findProductsByProductsID(index)?.image1 // หาก index เป็น 1
                2 -> ProductsService.findProductsByProductsID(index)?.image2 // หาก index เป็น 2
                3 -> ProductsService.findProductsByProductsID(index)?.image3 // หาก index เป็น 3
                4 -> ProductsService.findProductsByProductsID(index)?.image4 // หาก index เป็น 4
                else -> null // หาก index ไม่ตรงกับเงื่อนไขข้างต้น
            }

            if (imagePath != null) {
                val file = File(imagePath)
                call.respondFile(file)
            } else {
                call.respond(HttpStatusCode.NotFound, "ยังไม่มีรูป")
            }

        } catch (e: java.lang.NullPointerException) {
            // ดักจับข้อผิดพลาดเมื่อเจอ NPE และตอบกลับ "ยังไม่มีรูป"
            call.respond(HttpStatusCode.NotFound, "ยังไม่มีรูป")
        } catch (e: Exception) {
            // ดักจับข้อผิดพลาดอื่น ๆ และตอบกลับด้วยสถานะเฉพาะ
            call.respond(HttpStatusCode.InternalServerError, "เกิดข้อผิดพลาด: ${e.message}")
        }
    }

}
