package org.camrent.routes.api.customer

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.camrent.routes.api.customer.delete.CustomerDeleteByID
import org.camrent.routes.api.customer.get.*
import org.camrent.routes.api.customer.get.test.TestProfileURL
import org.camrent.routes.api.customer.patch.CustomerPatchByID
import org.camrent.routes.api.customer.post.CustomerUploadImage


fun Application.customerRoute() {

    routing {

        route("api/v1") {

            // `GET` "/customers" : ใช้เพื่อเรียกดูข้อมูลจาก ตาราง `Customers` ทั้งหมด
            CustomerGet()

            // `GET` "/customers/id/{id}" : ใช้สำหรับ ดึงขอมูล ของ `Customer` โดยใช้หมายเลข `ID` ในการขอ
            CustomerGetByID()

            // `GET` "/customers/img/id/{id}/idx/{idx}/{image}" : ใช้สำหรับ ดึงรูป `Profile` ของ `Customer`
            CustomerGetImageURL()

            // `GET` "/customers/img/id/{id}" : ใช้สำหรับ ดึงรูป `Profile` ของ `Customer`
            //CustomerOpenImage()

            // `GET` "/customers/name/{name}" ใช้สำหรับ ดึงขอมูล ของ `Customer` โดยใช้หมายเลข `User Name` ในการขอ
            CustomerByUserName()

            // `POST` "/customers/img/id/{id}"  ใช้สำหรับ เพิ่มรูป Profile ลงฐานข้อมูล `Status code HTTP: 201`
            CustomerUploadImage()

            // `PATCH` "/customers/id/{id}" : ใช้สำหรับ ข้อมูลต่างๆ ยกเว้นรูป
            CustomerPatchByID()

            // `DELETE` "/customers/id/{id}"
            CustomerDeleteByID()

            // TestProfileURL()
        }
    }

}