package org.camrent.utils

import io.ktor.http.content.*
import java.io.File

object AccountDirectory {

    fun getDirectoryPath(path: String): String = path

    fun createDirectory(typeUser: String, directoryID: Int): Boolean {

        // กำหนดเส้นทางของไดเร็กทอรีหลัก
        val baseDirectory = "src/main/resources/images/account/$typeUser/usr_$directoryID"

        // กำหนดเส้นทางของไดเร็กทอรีที่ต้องการสร้างโดยใช้ when expression
        val directoryPath = when (typeUser) {
            "customers" -> "$baseDirectory/profileImage"
            "stores" -> "$baseDirectory/profileImage/stores/products/camera; $baseDirectory/profileImage/stores/products/accessories"
            else -> throw IllegalArgumentException("ไม่รู้จัก Directory: $typeUser")
        }

        return try {
            // สร้างไดเร็กทอรีหากยังไม่มีอยู่
            val file = File(directoryPath)
            if (!file.exists()) {
                val created = file.mkdirs()
                if (created) {
                    println("ไดเร็กทอรี ไอดี '$directoryID' ถูกสร้างเรียบร้อยที่ '$typeUser'.")
                    this.getDirectoryPath(directoryPath)
                    true
                } else {
                    println("ไม่สามารถสร้างไดเร็กทอรี '$directoryID' ที่ '$typeUser' ได้.")
                    false
                }
            } else {
                println("ไดเร็กทอรี '$directoryID' มีอยู่แล้วที่ '$typeUser'.")
                false
            }
        } catch (e: Exception) {
            println("เกิดข้อผิดพลาด: ${e.message}")
            false
        }
    }


    fun deleteDirectory(typeUser: String, directoryID: Int): Boolean {
        val sanitizedPath = typeUser.replace(" ", "_")
        val fullPath = "src/main/resources/images/account/$sanitizedPath/usr_$directoryID"

        return try {
            val file = File(fullPath)
            if (file.exists() && file.isDirectory) {
                file.deleteRecursively()
                println("ลบไดเร็กทอรี '$directoryID' ที่ '$typeUser' สำเร็จแล้ว")
                true // ลบ Directory สำเร็จ
            } else {
                println("ไม่มีไดเรกทอรี '$directoryID' ที่ '$typeUser'")
                false // Directory ไม่มีอยู่
            }
        } catch (e: Exception) {
            println("เกิดข้อผิดพลาด: ${e.message}")
            false // เกิดข้อผิดพลาด
        }
    }


    fun saveImage(id: Int, typeAccount: String, typeImage: String, imagePart: PartData.FileItem): File {
        val fileName = imagePart.originalFileName ?: "image.jpg"

        val filePath = when {
            typeAccount == "customers" && typeImage == "profileImage" -> {
                // src/main/resources/images/account/customers/usr_1/profileImage
                File("src/main/resources/images/account/$typeAccount/usr_$id/$typeImage/$fileName")
            }

            typeAccount == "stores" && (typeImage == "profileImage" || typeImage == "camera" || typeImage == "accessories") -> {
                File("src/main/resources/images/account/$typeAccount/usr_$id/products/$typeImage/$fileName")
            }

            else -> {
                throw IllegalArgumentException("ประเภทบัญชีหรือประเภทรูปภาพไม่ถูกต้อง")
            }
        }

        // ใช้ข้อมูลจาก stream provider เพื่อบันทึกไฟล์
        imagePart.streamProvider().use { input ->
            filePath.outputStream().buffered().use { output ->
                input.copyTo(output)
            }
        }
        // ส่งคืนไฟล์ภาพที่ถูกบันทึก
        return filePath
    }



    fun deleteFilesInPathAndCheckExistence(filePath: String): Boolean {
        // สร้างอ็อบเจกต์ของ File จากที่กำหนดให้
        val fileToDelete = File(filePath)

        return try {
            // ตรวจสอบว่าไฟล์หรือเส้นทางที่ระบุมีอยู่หรือไม่
            if (!fileToDelete.exists() || !fileToDelete.isFile) {
                throw IllegalArgumentException("เส้นทางไฟล์ที่ระบุไม่ถูกต้องหรือไม่มีอยู่: $filePath")
            }

            // ทำการลบไฟล์
            val deleted = fileToDelete.delete()

            // ตรวจสอบผลการลบไฟล์และแสดงผลลัพธ์
            if (deleted) {
                println("ไฟล์ $filePath ถูกลบเรียบร้อยแล้ว.")
            } else {
                println("ไม่สามารถลบไฟล์: $filePath")
            }

            // ส่งค่าผลลัพธ์การลบไฟล์ออกไป
            deleted
        } catch (e: IllegalArgumentException) {
            // จัดการข้อผิดพลาดที่เกิดจากข้อมูลที่ไม่ถูกต้อง
            println("ข้อยกเว้น IllegalArgumentException: ${e.message}")
            false
        } catch (e: SecurityException) {
            // จัดการข้อผิดพลาดที่เกิดจากปัญหาด้านความปลอดภัย
            println("ข้อยกเว้น SecurityException: ${e.message}")
            false
        } catch (e: Exception) {
            // จัดการข้อผิดพลาดที่ไม่ได้ระบุไว้ล่วงหน้า
            println("เกิดข้อผิดพลาดที่ไม่คาดคิด: ${e.message}")
            false
        }
    }


}


