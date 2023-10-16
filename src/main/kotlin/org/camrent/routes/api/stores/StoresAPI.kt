package org.camrent.routes.api.stores

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.camrent.routes.api.stores.get.StoreGet
import org.camrent.routes.api.stores.get.StoreGetImageURL

fun Application.storesRoute() {

    routing {

        route("api/v1") {

            // `GET` "/stores" : ใช้เพื่อเรียกดูข้อมูลจาก ตาราง `Stores` ทั้งหมด
            StoreGet()

            // `GET` "/stores/img/id/{id}/{image}" : ใช้สำหรับ ดึงรูป `Profile` ของ `Store`
            StoreGetImageURL()

        }

    }

}