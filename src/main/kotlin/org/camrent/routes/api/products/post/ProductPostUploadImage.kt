package org.camrent.routes.api.products.post

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.ProductsService
import org.camrent.utils.AccountDirectory.saveImage
import java.io.File

fun Route.ProductPostUploadImage() {

    post("products/img/id/{id}") {

        // ดึงค่า id จากพารามิเตอร์และแปลงเป็น Int ถ้าเป็นไปได้
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

        val productData = ProductsService.findProductsByID(id)
            ?: throw NotFoundException("ไม่พบสินค้าสำหรับ `ID`: $id")

        val img1 = productData.image1
        val img2 = productData.image2
        val img3 = productData.image3
        val img4 = productData.image4

        // รับข้อมูลจาก multipart request
        val multipart = call.receiveMultipart()

        var imageFile: File? = null
        var reciveValue: String? = null
        var reciveName: String? = null

        multipart.forEachPart { part ->
            when (part) {

                is PartData.FormItem -> {
                    val formItemValue = part.value
                    val formItemName = part.name
                    println("Name: ${part.name} Value: $formItemValue")
                    reciveValue = formItemValue  // บันทึกค่า formItemValue
                    reciveName = formItemName
                }

                is PartData.FileItem -> {
                    // บันทึกไฟล์ภาพลงในระบบ
                    imageFile = saveImage(id, "products", "productImage", part)
                }

                is PartData.BinaryChannelItem, is PartData.BinaryItem -> {
                    // ไม่รองรับข้อมูล Binary
                    call.respond(HttpStatusCode.UnsupportedMediaType, "ไม่รองรับข้อมูล Binary")
                    return@forEachPart
                }

            }

        }

    }

}