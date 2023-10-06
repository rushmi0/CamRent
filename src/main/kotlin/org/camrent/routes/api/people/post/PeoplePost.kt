package org.camrent.routes.api.people.post

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.field.PeopleField
import org.camrent.database.forms.PeopleForm
import org.camrent.database.record.PeopleRecord
import org.camrent.database.service.CustomerService
import org.camrent.database.service.PeopleService
import org.camrent.database.service.StoresService
import org.camrent.security.xss.XssDetector

fun Route.PeoplePost() {

    post("people") {
        try {
            val payload = call.receive<PeopleForm>()

            // ข้อมูลที่รับมาจาก `client`
            val firstName = payload.firstName
            val lastName = payload.lastName
            val email = payload.email
            val phoneNumber = payload.phoneNumber

            // หมายเลข `ID` ที่จะใช้ในการ update ข้อมูล จาก `People Table`
            val targetID = call.request.headers["AccountID"]?.toInt()!!
            println("AccountID: $targetID")

            /**
             * @sample Customer :
             * @sample Stores :
             * */
            val receiveType = call.request.headers["AccountType"]
            println("AccountType: $receiveType")

            // * ตรวจสอบว่า ค่าที่รับมาจาก `client` ไม่เป็นค่าว่าง
            if (
                firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                email.isNotBlank() &&
                phoneNumber.isNotBlank()
            ) {

                // * ถ้าเงื่อไข if แรกเป็นจริง ในส่วนนี้จะทำงาน เพื่อตรวจสอบ XSS
                if (
                    XssDetector.containsXss(firstName) ||
                    XssDetector.containsXss(lastName) ||
                    XssDetector.containsXss(email) ||
                    XssDetector.containsXss(phoneNumber)
                ) {

                    // * ถ้าพบ Cross-site Scripting (XSS), ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "ตรวจพบการเขียน Cross-site scripting"
                    )

                } else {


                    if (receiveType == "Customer") {
                        // * นำข้อมูลที่รับมาจาก `client` บันทึกลงฐานข้อมูล และจะคือค่า `true` เพื่อบันทึกสำเร็จ
                        val statement = PeopleService.insert(
                            PeopleForm(
                                firstName,
                                lastName,
                                email,
                                phoneNumber
                            )
                        )

                        if (statement) {

                            /**
                             * เมื่อบันทึกสำเร็จ ข้อมูลชุดนั้นจะได้รับหมายเลข ID
                             * จากนั้นนำหมายเลข People ID ไปใส่ใน `Customers Table` คนที่ต้องการ
                             * โดยอ้างอิงจากหมายเลข `Customers ID` ที่รับมาจาก client
                             *
                             * โดยมีขั้นตอนวิธีดังนี้:
                             * @exception peopleRecord: ดึงข้อมูลจาก `Field` ที่ต้องการโดยใช้ Email ในการหา เมื่อพบจะได้รับข้อมูลทั้งหมด
                             * @exception idPeople: ดึงเอาเฉพาะ `ID` จาก `Field` นั้นที่หามา
                             * */

                            // ค้นหาข้อมูลบุคคลด้วยอีเมล
                            val peopleRecord: PeopleField? = PeopleService.findPeopleByEmail(email)

                            // ดึงเอา ID ของบุคคล (personID) ถ้าพบ
                            val idPeople: Int? = peopleRecord?.personID

                            // ค้นหาข้อมูลลูกค้าด้วยรหัสผู้ใช้
                            val checkID = CustomerService.findCustomerByUserID(targetID)?.customerID

                            // ตรวจสอบว่ารหัสผู้ใช้ตรงกับที่รับมาหรือไม่
                            if (checkID == targetID) {

                                val updateStatement = CustomerService.update(
                                    targetID,
                                    "PersonID", // ค่านี้ต้องตรงกับชื่อ Field ในฐานข้อมูลจริง (ตัวอย่าง: ชื่อ Field ในฐานข้อมูลเป็น "PersonID")
                                    idPeople.toString() // แปลง ID ของบุคคลเป็น String เพื่ออัปเดต
                                )

                                // ถ้าอัปเดตสำเร็จ
                                if (updateStatement) {

                                    // ค้นหาข้อมูลลูกค้าอีกครั้ง
                                    val customerRecord = CustomerService.findCustomerByUserID(targetID)
                                    println(customerRecord)

                                    // ส่งข้อมูลลูกค้ากลับไปหา client
                                    call.respond(
                                        HttpStatusCode.OK,
                                        "บันทึกข้อมูล `Customer` สำเร็จ"
                                    )
                                }

                            }
                        } else {
                            call.respond(
                                HttpStatusCode.BadRequest,
                                "ข้อมูลบุคคล ห้ามซ้ำกัน"
                            )
                        }
                    }




                    if (receiveType == "Stores") {
                        // * นำข้อมูลที่รับมาจาก `client` บันทึกลงฐานข้อมูล และจะคือค่า `true` เพื่อบันทึกสำเร็จ
                        val statement = PeopleService.insert(
                            PeopleForm(
                                firstName,
                                lastName,
                                email,
                                phoneNumber
                            )
                        )

                        if (statement) {

                            /**
                             * เมื่อบันทึกสำเร็จ ข้อมูลชุดนั้นจะได้รับหมายเลข ID
                             * จากนั้นนำหมายเลข People ID ไปใส่ใน `Customers Table` คนที่ต้องการ
                             * โดยอ้างอิงจากหมายเลข `Customers ID` ที่รับมาจาก client
                             *
                             * โดยมีขั้นตอนวิธีดังนี้:
                             * @exception peopleRecord: ดึงข้อมูลจาก `Field` ที่ต้องการโดยใช้ Email ในการหา เมื่อพบจะได้รับข้อมูลทั้งหมด
                             * @exception idPeople: ดึงเอาเฉพาะ `ID` จาก `Field` นั้นที่หามา
                             * */

                            // ค้นหาข้อมูลบุคคลด้วยอีเมล
                            val peopleRecord: PeopleField? = PeopleService.findPeopleByEmail(email)

                            // ดึงเอา ID ของบุคคล (personID) ถ้าพบ
                            val idPeople: Int? = peopleRecord?.personID

                            // ค้นหาข้อมูลร้านด้วยรหัสผู้ใช้
                            val checkID = StoresService.findStoresByUserID(targetID)?.storeID

                            // ตรวจสอบว่ารหัสผู้ใช้ตรงกับที่รับมาหรือไม่
                            if (checkID == targetID) {

                                val updateStatement = StoresService.update(
                                    targetID,
                                    "PersonID", // ค่านี้ต้องตรงกับชื่อ Field ในฐานข้อมูลจริง (ตัวอย่าง: ชื่อ Field ในฐานข้อมูลเป็น "PersonID")
                                    idPeople.toString() // แปลง ID ของบุคคลเป็น String เพื่ออัปเดต
                                )

                                // ถ้าอัปเดตสำเร็จ
                                if (updateStatement) {

                                    // ค้นหาข้อมูลลูกค้าอีกครั้ง
                                    val customerRecord = StoresService.findStoresByUserID(targetID)!!
                                    println(customerRecord)

                                    // ส่งข้อมูลลูกค้ากลับไปหา client
                                    call.respond(
                                        HttpStatusCode.OK,
                                        "บันทึกข้อมูล `Stores` สำเร็จ"
                                    )

                                }

                            }
                        } else {
                            call.respond(
                                HttpStatusCode.BadRequest,
                                "ข้อมูลบุคคล ห้ามซ้ำกัน"
                            )
                        }
                    }


                }

            } else {

                call.respond(
                    HttpStatusCode.BadRequest,
                    "ข้อมูลบุคคลต้องไม่ว่างเปล่า"
                )

            }

        } catch (e: ContentTransformationException) {

            // ถ้าข้อมูลไม่ตรงตามรูปแบบ, ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
            call.respond(
                HttpStatusCode.BadRequest,
                "รูปแบบข้อมูลไม่ถูกต้อง กรุณาระบุข้อมูลในรูปแบบที่ถูกต้อง"
            )

        } catch (e: Exception) {
            // ถ้าเกิดข้อผิดพลาดอื่น ๆ, ตอบกลับด้วยสถานะผลลัพธ์ 500 Internal Server Error
            call.respond(
                HttpStatusCode.InternalServerError,
                "เกิดข้อผิดพลาดขณะประมวลผลคำขอของคุณ ข้อผิดพลาด: ${e.message}"
            )
        }

    }

}