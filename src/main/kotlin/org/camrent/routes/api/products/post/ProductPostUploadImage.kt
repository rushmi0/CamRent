package org.camrent.routes.api.products.post

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.ProductsService
import org.camrent.database.service.StoresService
import org.camrent.security.securekey.ECDSA
import org.camrent.security.securekey.EllipticCurve.getDecompress
import org.camrent.security.securekey.Sha256
import org.camrent.utils.AccountDirectory.deleteFilesInPathAndCheckExistence
import org.camrent.utils.AccountDirectory.saveImage
import org.camrent.utils.ShiftTo.B32decode
import org.camrent.utils.ShiftTo.ByteArrayToBigInteger
import java.io.File

fun Route.ProductUploadImage() {

    post("products/img/id/{id}") {

        try {

            val typeProduct = call.request.headers["ProductType"]
                ?: throw IllegalArgumentException("`ProductType` ไม่ถูกต้องหรือไม่ได้ระบุ")

            val typeImage = call.request.headers["ImageType"]
                ?: throw IllegalArgumentException("`ImageType` ไม่ถูกต้องหรือไม่ได้ระบุ")


            // ดึงค่า id จากพารามิเตอร์และแปลงเป็น Int ถ้าเป็นไปได้
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

            val productData = ProductsService.findProductsByID(id)
                ?: throw NotFoundException("ไม่พบสินค้าสำหรับ `ID`: $id")


            // ดึงชื่อไฟล์รูปภาพของสินค้า
            val img1 = productData.image1
            val img2 = productData.image2
            val img3 = productData.image3
            val img4 = productData.image4

            // รับข้อมูลจาก multipart request
            val multipart = call.receiveMultipart()

            var imageFile: File? = null
            var reciveName: String? = null
            var reciveValue: String? = null

            multipart.forEachPart { part ->
                when (part) {

                    is PartData.FormItem -> {
                        val formItemValue = part.value // ค่าฟิลด์
                        val formItemName = part.name // ชื่อฟิลด์
                        println("Name: ${part.name} Value: $formItemValue")
                        reciveValue = formItemValue  // บันทึกค่า formItemValue
                        reciveName = formItemName // บันทึกค่า formItemName


                        // เงื่อนไขการทำงานตามชื่อฟิลด์ภาพ (Image1, Image2, Image3, Image4) และค่าที่รับมา (1, 2, 3, 4)
                        when {
                            reciveName == "Image1" && reciveValue == "1" -> {
                                // ถ้าชื่อฟิลด์เป็น Image1 และค่าที่รับมาเป็น 1
                                if (img1 == "N/A") {
                                    // ถ้าไม่มีรูปภาพเดิม (N/A) ใน Image1
                                    // อัปเดตข้อมูลสินค้าในฐานข้อมูลเพื่อเพิ่มรูปภาพใหม่
                                    ProductsService.update(
                                        productData.productID,
                                        "Image1",
                                        "${imageFile?.absolutePath}"
                                    )
                                    println("Inert Name Field: $reciveName Value Field: $reciveValue")
                                    call.respond(HttpStatusCode.Created)  // ตอบกลับว่าอัปโหลดไฟล์เรียบร้อย
                                } else {
                                    // ถ้ามีรูปภาพเดิมใน Image1
                                    // ลบไฟล์รูปภาพเดิมจากระบบ
                                    val success = deleteFilesInPathAndCheckExistence(img1)
                                    if (success) {
                                        // อัปเดตข้อมูลสินค้าในฐานข้อมูลเพื่อเปลี่ยนรูปภาพ
                                        ProductsService.update(
                                            productData.productID,
                                            "Image1",
                                            "${imageFile?.absolutePath}"
                                        )
                                        println("Update Name Field: $reciveName Value Field: $reciveValue")
                                        call.respond(HttpStatusCode.Created)  // ตอบกลับว่าอัปโหลดไฟล์เรียบร้อย
                                    } else {
                                        // ตอบกลับว่าไม่สามารถลบไฟล์รูปภาพเดิมได้
                                        call.respond(
                                            HttpStatusCode.InternalServerError,
                                            "ไม่สามารถลบไฟล์รูปภาพเดิมจากระบบ"
                                        )
                                    }
                                }
                            }

                            reciveName == "Image2" && reciveValue == "2" -> {
                                // ถ้าชื่อฟิลด์เป็น Image2 และค่าที่รับมาเป็น 2
                                if (img1 == "N/A") {
                                    // ถ้าไม่มีรูปภาพเดิม (N/A) ใน Image2
                                    // อัปเดตข้อมูลสินค้าในฐานข้อมูลเพื่อเพิ่มรูปภาพใหม่
                                    ProductsService.update(
                                        productData.productID,
                                        "Image2",
                                        "${imageFile?.absolutePath}"
                                    )
                                    println("Inert Name Field: $reciveName Value Field: $reciveValue")
                                    call.respond(HttpStatusCode.Created)  // ตอบกลับว่าอัปโหลดไฟล์เรียบร้อย
                                } else {
                                    // ถ้ามีรูปภาพเดิมใน Image2
                                    // ลบไฟล์รูปภาพเดิมจากระบบ
                                    val success = deleteFilesInPathAndCheckExistence(img2)
                                    if (success) {
                                        // อัปเดตข้อมูลสินค้าในฐานข้อมูลเพื่อเปลี่ยนรูปภาพ
                                        ProductsService.update(
                                            productData.productID,
                                            "Image2",
                                            "${imageFile?.absolutePath}"
                                        )
                                        println("Update Name Field: $reciveName Value Field: $reciveValue")
                                        call.respond(HttpStatusCode.Created)  // ตอบกลับว่าอัปโหลดไฟล์เรียบร้อย
                                    } else {
                                        // ตอบกลับว่าไม่สามารถลบไฟล์รูปภาพเดิมได้
                                        call.respond(
                                            HttpStatusCode.InternalServerError,
                                            "ไม่สามารถลบไฟล์รูปภาพเดิมจากระบบ"
                                        )
                                    }
                                }
                            }

                            reciveName == "Image3" && reciveValue == "3" -> {
                                // ถ้าชื่อฟิลด์เป็น Image3 และค่าที่รับมาเป็น 3
                                if (img1 == "N/A") {
                                    // ถ้าไม่มีรูปภาพเดิม (N/A) ใน Image3
                                    // อัปเดตข้อมูลสินค้าในฐานข้อมูลเพื่อเพิ่มรูปภาพใหม่
                                    ProductsService.update(
                                        productData.productID,
                                        "Image3",
                                        "${imageFile?.absolutePath}"
                                    )
                                    println("Inert Name Field: $reciveName Value Field: $reciveValue")
                                    call.respond(HttpStatusCode.Created)  // ตอบกลับว่าอัปโหลดไฟล์เรียบร้อย
                                } else {
                                    // ถ้ามีรูปภาพเดิมใน Image3
                                    // ลบไฟล์รูปภาพเดิมจากระบบ
                                    val success = deleteFilesInPathAndCheckExistence(img3)
                                    if (success) {
                                        // อัปเดตข้อมูลสินค้าในฐานข้อมูลเพื่อเปลี่ยนรูปภาพ
                                        ProductsService.update(
                                            productData.productID,
                                            "Image3",
                                            "${imageFile?.absolutePath}"
                                        )
                                        println("Update Name Field: $reciveName Value Field: $reciveValue")
                                        call.respond(HttpStatusCode.Created)  // ตอบกลับว่าอัปโหลดไฟล์เรียบร้อย
                                    } else {
                                        // ตอบกลับว่าไม่สามารถลบไฟล์รูปภาพเดิมได้
                                        call.respond(
                                            HttpStatusCode.InternalServerError,
                                            "ไม่สามารถลบไฟล์รูปภาพเดิมจากระบบ"
                                        )
                                    }
                                }
                            }

                            reciveName == "Image4" && reciveValue == "4" -> {
                                // ถ้าชื่อฟิลด์เป็น Image4 และค่าที่รับมาเป็น 4
                                if (img1 == "N/A") {
                                    // ถ้าไม่มีรูปภาพเดิม (N/A) ใน Image4
                                    // อัปเดตข้อมูลสินค้าในฐานข้อมูลเพื่อเพิ่มรูปภาพใหม่
                                    ProductsService.update(
                                        productData.productID,
                                        "Image4",
                                        "${imageFile?.absolutePath}"
                                    )
                                    println("Inert Name Field: $reciveName Value Field: $reciveValue")
                                    call.respond(HttpStatusCode.Created)  // ตอบกลับว่าอัปโหลดไฟล์เรียบร้อย
                                } else {
                                    // ถ้ามีรูปภาพเดิมใน Image4
                                    // ลบไฟล์รูปภาพเดิมจากระบบ
                                    val success = deleteFilesInPathAndCheckExistence(img4)
                                    if (success) {
                                        // อัปเดตข้อมูลสินค้าในฐานข้อมูลเพื่อเปลี่ยนรูปภาพ
                                        ProductsService.update(
                                            productData.productID,
                                            "Image4",
                                            "${imageFile?.absolutePath}"
                                        )
                                        println("Update Name Field: $reciveName Value Field: $reciveValue")
                                        call.respond(HttpStatusCode.Created)  // ตอบกลับว่าอัปโหลดไฟล์เรียบร้อย
                                    } else {
                                        // ตอบกลับว่าไม่สามารถลบไฟล์รูปภาพเดิมได้
                                        call.respond(
                                            HttpStatusCode.InternalServerError,
                                            "ไม่สามารถลบไฟล์รูปภาพเดิมจากระบบ"
                                        )
                                    }
                                }
                            }
                        }

                    }

                    is PartData.FileItem -> {
                        // บันทึกไฟล์ภาพลงในระบบ

                        imageFile = saveImage(
                            part,
                            id,
                            "stores",
                            typeImage, // camera, accessories
                            typeProduct, // compact, mirrorless, dslr
                        )
                    }

                    is PartData.BinaryChannelItem, is PartData.BinaryItem -> {
                        // ไม่รองรับข้อมูล Binary
                        call.respond(
                            HttpStatusCode.UnsupportedMediaType,
                            "ไม่รองรับข้อมูล Binary"
                        )
                        return@forEachPart
                    }

                }

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