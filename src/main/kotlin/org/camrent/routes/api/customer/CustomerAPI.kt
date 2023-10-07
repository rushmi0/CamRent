package org.camrent.routes.api.customer


import io.ktor.server.application.*
import io.ktor.server.routing.*

import org.camrent.routes.api.customer.delete.CustomerDeleteByID
import org.camrent.routes.api.customer.get.*
import org.camrent.routes.api.customer.patch.CustomerPatchByID
import org.camrent.routes.api.customer.patch.CustomerUploadImage
import org.camrent.routes.api.customer.post.CustomerPost


fun Application.customerRoute() {

    routing {

        route("api/v1") {


            // `GET` "/customers" : ใช้เพื่อเรียกดูข้อมูลจาก ตาราง `Customers` ทั้งหมด
            CustomerGet()

            // `GET` "/customers/auth/{publicKey}" : ใช้สำหรับ `Sign-in` และจะได้รับข้อมูลที่สำคัญ
            CustomerAuthentication()

            // `GET` "/customers/id/{id}"  ใช้สำหรับ ดึงขอมูล ของ `Customer` โดยใช้หมายเลข `ID` ในการขอ
            CustomerGetByID()

            // `GET` "/customers/img/id/{id}" : ใช้สำหรับ ดึงรูป `Profile` ของ `Customer`
            CustomerOpenImage()

            // `GET` "/customers/name/{name}" ใช้สำหรับ ดึงขอมูล ของ `Customer` โดยใช้หมายเลข `User Name` ในการขอ
            CustomerByUserName()

            // `POST` "/customers" : ใช้สำหรับ `Sign-up` และบันทึกลงฐานข้อมูล
            CustomerPost()

            // `PATCH` "/customers/id/{id}" : ใช้สำหรับ ข้อมูลต่างๆ ยกเว้นรูป
            CustomerPatchByID()

            // `PATCH` "/customers/img/id/{id}"  ใช้สำหรับ เพิ่มรูป ลงฐานข้อมูล
            CustomerUploadImage()

            // `DELETE` "/customers/id/{id}"
            CustomerDeleteByID()

        }
    }

}