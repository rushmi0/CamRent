package org.camrent.routes.api.people

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import org.camrent.security.xss.XssDetector
import org.camrent.database.forms.PeopleForm
import org.camrent.database.service.PeopleService
import org.camrent.routes.api.people.get.PeopleGet
import org.camrent.routes.api.people.get.PeopleGetByID
import org.camrent.routes.api.people.patch.PeoplePatchByID
import org.camrent.routes.api.people.post.PeoplePost

fun Application.peopleRoute() {

    routing {

        route("api/v1") {

            // `GET` "/people" : ใช้เพื่อเรียกดูข้อมูลจาก ตาราง `People` ทั้งหมด
            PeopleGet()

            // `GET` "people/id/{id}" ใช้สำหรับ ดึงขอมูล ของ `People` โดยใช้หมายเลข `ID` ในการขอ
            PeopleGetByID()

            // `PATCH` "/people/id/{id}" ใช้สำหรับ ข้อมูลต่างๆ
            PeoplePatchByID()

            // `POST` "/people" : ใช้สำหรับ เพิ่มข้อมูลส่วนบุคคล โดยการรับข้อมูลหลัก และหมายเลข `ID` ของ `User` นั้น
            PeoplePost()

        }

    }


}