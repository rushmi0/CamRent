package org.camrent.utils

import java.io.File

object AccountDirectory {

    fun getDirectoryPath(path: String): String = path

    fun createDirectory(typeUser: String, directoryID: Int): Boolean {
        // แทนที่ช่องว่างด้วย underscores ในชื่อผู้ใช้
        val sanitizedPath = typeUser.replace(" ", "_")
        // กำหนดเส้นทางของไดเร็กทอรีหลัก
        val baseDirectory = "src/main/resources/images/account/$sanitizedPath/usr_$directoryID"

        // กำหนดเส้นทางของไดเร็กทอรีที่ต้องการสร้างโดยใช้ when expression
        val directoryPath = when (typeUser) {
            "customers" -> "$baseDirectory/profileImage/customers"
            "stores" -> "$baseDirectory/profileImage/stores/products/camera; $baseDirectory/profileImage/stores/products/accessories"
            else -> throw IllegalArgumentException("ไม่รู้จัก Directory: $typeUser")
        }

        return try {
            // สร้างไดเร็กทอรีหากยังไม่มีอยู่
            val file = File(directoryPath)
            if (!file.exists()) {
                val created = file.mkdirs()
                if (created) {
                    println("Directory '$directoryID' ถูกสร้างเรียบร้อยที่ '$typeUser'.")
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
                println("Directory '$directoryID' deleted successfully at '$typeUser'.")
                true // ลบ Directory สำเร็จ
            } else {
                println("Directory '$directoryID' does not exist at '$typeUser'.")
                false // Directory ไม่มีอยู่
            }
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            false // เกิดข้อผิดพลาด
        }
    }

}