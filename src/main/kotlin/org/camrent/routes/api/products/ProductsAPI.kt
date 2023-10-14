package org.camrent.routes.api.products

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.camrent.routes.api.products.delete.ProductDeleteByID
import org.camrent.routes.api.products.get.ProductGet
import org.camrent.routes.api.products.get.ProductGetProductByID
import org.camrent.routes.api.products.get.ProductGetProductByStoreID
import org.camrent.routes.api.products.patch.ProductPatchByID
import org.camrent.routes.api.products.post.ProductPost
import org.camrent.routes.api.products.post.ProductUploadImage


fun Application.productsRoute() {

    routing {

        route("api/v1") {

            // `GET` "/product" : ใช้เรียกดูรายการสินค้า ทั้งหมดในฐานข้อมูล
            ProductGet()

            // `GET` "/product/id/{id}"
            ProductGetProductByID()

            // `GET` "/product/store-id/{id}" : ใช้เพื่อหาข้อมูลสินค้าที่มี `StoreID` เท่ากับ หายเลข `ID` ที่รับเข้ามาและคืนค่าเป็น `List objects` ที่พบในฐานข้อมูล
            /**
             * SELECT *
             * FROM Products
             * WHERE StoreID = ID;
             * */
            ProductGetProductByStoreID()

            // `POST` "/product" : ใช้สำหรับ เพิ่มข้อมูลสินค่า
            ProductPost()

            // `POST` "/products/img/id/{id}" : ใช้สำหรับ อัปโหลดรูปภาพสินค้าตาม ID ที่ระบุ
            ProductUploadImage()

            // `PATCH` "/product/id/{id}" ใช้สำหรับ ข้อมูลต่างๆ ยกเว้นรูปตาม ID ที่ระบุ
            ProductPatchByID()

            // `DELETE` "/product/id/{id}" : ใช้สำหรับ ลบข้อมูลสินค้าตาม ID ที่ระบุ
            ProductDeleteByID()
        }

    }

}