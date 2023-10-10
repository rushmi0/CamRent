package org.camrent.routes.api.auth.post

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.field.CustomersField
import org.camrent.database.forms.CustomersForm
import org.camrent.database.forms.StoresForm
import org.camrent.database.record.NewAccount
import org.camrent.database.service.CustomerService
import org.camrent.database.service.StoresService
import org.camrent.security.xss.XssDetector
import org.camrent.utils.AccountDirectory


fun Route.SignUpNewAccount() {


    post("user/sign-up") {

        try {

            val accountType = call.request.headers["AccountType"]

            val payload = call.receive<NewAccount>()

            val userName = payload.userName
            val publicKey = payload.authKey


            when {

                // Sign-up บช. `Customers`
                accountType == "Customers" -> {
                    // ตรวจสอบว่า `UserName` และ `AuthKey` ไม่เป็นค่าว่าง
                    if (userName.isNotBlank() && publicKey.isNotBlank()) {
                        // ตรวจสอบ XSS
                        if (XssDetector.containsXss(userName) ||
                            XssDetector.containsXss(publicKey)
                        ) {

                            // ถ้าพบ Cross-site Scripting (XSS), ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                            call.respond(
                                HttpStatusCode.BadRequest,
                                "ตรวจพบการเขียน Cross-site scripting"
                            )

                        } else {

                            val checkUserName = CustomerService.findCustomerByUserName(userName)?.userName

                            if (checkUserName == userName) {
                                call.respond(
                                    HttpStatusCode.BadRequest,
                                    "User Name ใช้งานไม่ได้"
                                )
                            } else {

                                // เพิ่มข้อมูลลูกค้า ถ้าทำสำเร็จจะคือค่าเป็น true
                                val statement = CustomerService.insert(
                                    CustomersForm(
                                        userName,
                                        publicKey
                                    )
                                )

                                println(statement)

                                if (statement) {
                                    // เพื่อตอบกลับให้กับ client, ตรวจสอบว่าการค้นหาผู้ใช้ด้วย `userName` สำเร็จหรือไม่
                                    val customerRecord: CustomersField? = CustomerService.findCustomerByUserName(userName)
                                    val id: Int? = customerRecord?.customerID
                                    val mkdir = AccountDirectory.createDirectory("customers", id!!)

                                    if (mkdir) {
                                        // ส่งข้อมูลลูกค้าที่ค้นหาได้กลับไปยัง client
                                        call.respond(HttpStatusCode.OK, customerRecord)
                                        println("สร้างบัญชี Customers สำเร็จ \n" + customerRecord)
                                    }

                                }
                            }

                        }
                    } else {
                        // ถ้า UserName หรือ AuthKey ว่าง, ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "UserName และ AuthKey จำเป็นต้องระบุข้อมูล"
                        )
                    }
                }

                // Sign-up บช. `Stores`
                accountType == "Stores" -> {
                    // ตรวจสอบว่า `UserName` และ `AuthKey` ไม่เป็นค่าว่าง
                    if (userName.isNotBlank() && publicKey.isNotBlank()) {
                        // ตรวจสอบ XSS
                        if (XssDetector.containsXss(userName) ||
                            XssDetector.containsXss(publicKey)
                        ) {

                            // ถ้าพบ Cross-site Scripting (XSS), ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                            call.respond(
                                HttpStatusCode.BadRequest,
                                "ตรวจพบการเขียน Cross-site scripting"
                            )

                        } else {

                            val checkUserName = StoresService.findStoresByUserName(userName)?.storeName

                            if (checkUserName == userName) {
                                call.respond(
                                    HttpStatusCode.BadRequest,
                                    "User Name ใช้งานไม่ได้"
                                )
                            } else {

                                // เพิ่มข้อมูลลูกค้า ถ้าทำสำเร็จจะคือค่าเป็น true
                                val statement = StoresService.insert(
                                    StoresForm(
                                        userName,
                                        publicKey
                                    )
                                )
                                println(statement)

                                if (statement) {

                                    // เพื่อตอบกลับให้กับ client, ตรวจสอบว่าการค้นหาผู้ใช้ด้วย `userName` สำเร็จหรือไม่
                                    val storesRecord = StoresService.findStoresByUserName(userName)
                                    val id: Int? = storesRecord?.storeID
                                    val mkdir = AccountDirectory.createDirectory("stores", id!!)

                                    if (mkdir) {
                                        // ส่งข้อมูลลูกค้าที่ค้นหาได้กลับไปยัง client
                                        call.respond(HttpStatusCode.OK, storesRecord)
                                        println("สร้างบัญชี Stores สำเร็จ \n" + storesRecord)
                                    }

                                }
                            }

                        }
                    } else {
                        // ถ้า UserName หรือ AuthKey ว่าง, ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "UserName และ AuthKey จำเป็นต้องระบุข้อมูล"
                        )
                    }
                }


                else -> {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "ประเภทบัญชี่ $accountType ที่ส่งมาเราไม่รองรับ"
                    )
                }

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