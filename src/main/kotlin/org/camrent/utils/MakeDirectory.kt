package org.camrent.utils

import java.io.File

object MakeDirectory {

    fun createDirectory(path: String, directoryID: Int): Boolean {
        val sanitizedPath = path.replace(" ", "_") // แทนที่ช่องว่างด้วย underscores
        val fullPath = "src/main/resources/images/account/$sanitizedPath/usr_$directoryID"

        return try {
            val file = File(fullPath)
            if (!file.exists()) {
                val created = file.mkdirs()
                if (created) {
                    println("Directory '$directoryID' created successfully at '$path'.")
                    true // สร้าง Directory สำเร็จ
                } else {
                    println("Failed to create directory '$directoryID' at '$path'.")
                    false // ไม่สามารถสร้าง Directory ได้
                }
            } else {
                println("Directory '$directoryID' already exists at '$path'.")
                false // Directory มีอยู่แล้ว
            }
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            false // เกิดข้อผิดพลาด
        }
    }


    fun deleteDirectory(path: String, directoryID: Int): Boolean {
        val sanitizedPath = path.replace(" ", "_")
        val fullPath = "src/main/resources/images/account/$sanitizedPath/usr_$directoryID"

        return try {
            val file = File(fullPath)
            if (file.exists() && file.isDirectory) {
                file.deleteRecursively()
                println("Directory '$directoryID' deleted successfully at '$path'.")
                true // ลบ Directory สำเร็จ
            } else {
                println("Directory '$directoryID' does not exist at '$path'.")
                false // Directory ไม่มีอยู่
            }
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            false // เกิดข้อผิดพลาด
        }
    }


}

fun main() {

    val id = 0

    val customersPath = "customers"
    MakeDirectory.createDirectory(customersPath, id)

    val storesPath = "stores"
    MakeDirectory.createDirectory(storesPath, id)

    val target = 0
    MakeDirectory.deleteDirectory(customersPath, target)
    MakeDirectory.deleteDirectory(storesPath, target)
}
