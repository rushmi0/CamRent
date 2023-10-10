package org.camrent.routes.api.customer.get.test

import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun Route.TestProfileURL() {

    get("preview/img/1") {

        val img1 = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/1.jpg"
        val img2 = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/2.jpg"

    }

    get("preview/img/1/{fileImage}") {


    }

}





fun main() {
    val filePath = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/account/customers/usr_1/profileImage/8.jpg"

    // ใช้ฟังก์ชัน substringAfterLast() เพื่อตัดข้อความจากตัวหลัง "/"
    val fileName = filePath.substringAfterLast("/")

    // พิมพ์ชื่อไฟล์
    println("File name: $fileName")
}
