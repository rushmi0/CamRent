package org.camrent.database.service


import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

import org.camrent.utils.Time.getCurrentDate
import org.camrent.utils.Time.getCurrentTime
import org.camrent.database.DatabaseFactory
import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.CustomersField
import org.camrent.database.forms.CustomersForm
import org.camrent.database.service.Customer.findCustomerByUserName
import org.camrent.database.service.Customer.selectMaxID
import org.camrent.database.service.Customer.selectMinID
import org.camrent.database.table.CustomersTable
import org.camrent.database.table.CustomersTable.authKey
import org.camrent.database.table.CustomersTable.createAt
import org.camrent.database.table.CustomersTable.customerID
import org.camrent.database.table.CustomersTable.personID
import org.camrent.database.table.CustomersTable.profileImage
import org.camrent.database.table.CustomersTable.timeStamp
import org.camrent.database.table.CustomersTable.userName


// สร้างอ็อบเจกต์ Customer สำหรับจัดการข้อมูลลูกค้า
object Customer {


    // เมธอดสำหรับดึงค่า ID ที่มากที่สุด
    suspend fun selectMaxID(): Int {
        return dbQuery {
            CustomersTable.slice(customerID.max())
                .selectAll()
                .singleOrNull()
                ?.getOrNull(customerID.max()) ?: 0
        }
    }

    // เมธอดสำหรับดึงค่า ID ที่น้อยที่สุด
    suspend fun selectMinID(): Int {
        return dbQuery {
            CustomersTable.slice(customerID.min())
                .selectAll()
                .singleOrNull()
                ?.getOrNull(customerID.min()) ?: 0
        }
    }


    // * เมธอดสำหรับดึงข้อมูลลูกค้าทั้งหมด
    suspend fun selectAllFromCustomers(): List<CustomersField> {
        return dbQuery {
            // ดึงข้อมูลทั้งหมดจากตาราง `CustomersTable` แล้วแปลงให้อยู่ในรูปของ `CustomersField`
            CustomersTable.selectAll().map {
                CustomersField(
                    it[customerID],     // ดึงค่า customerID จากฐานข้อมูล
                    it[userName],       // ดึงค่า userName จากฐานข้อมูล
                    it[profileImage],   // ดึงค่า profileImage จากฐานข้อมูล
                    it[authKey],        // ดึงค่า authKey จากฐานข้อมูล
                    it[timeStamp],      // ดึงค่า timeStamp จากฐานข้อมูล
                    it[createAt],       // ดึงค่า createAt จากฐานข้อมูล
                    it[personID]        // ดึงค่า personID จากฐานข้อมูล
                )
            }
        }
    }

    suspend fun findCustomerByUserID(userID: Int): CustomersField?{
        return dbQuery {
            CustomersTable.select { CustomersTable.customerID eq userID }
                .mapNotNull {
                    // แปลงข้อมูลที่ดึงมาในแต่ละแถวเป็น CustomersField object
                    CustomersField(
                        it[CustomersTable.customerID],  // ดึงค่า customerID จากฐานข้อมูล
                        it[CustomersTable.userName],    // ดึงค่า userName จากฐานข้อมูล
                        it[CustomersTable.profileImage], // ดึงค่า profileImage จากฐานข้อมูล
                        it[CustomersTable.authKey],      // ดึงค่า authKey จากฐานข้อมูล
                        it[CustomersTable.timeStamp],    // ดึงค่า timeStamp จากฐานข้อมูล
                        it[CustomersTable.createAt],     // ดึงค่า createAt จากฐานข้อมูล
                        it[CustomersTable.personID]      // ดึงค่า personID จากฐานข้อมูล
                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }

    suspend fun findCustomerByUserName(accountName: String): CustomersField? {
        return dbQuery {
            // เลือกข้อมูลลูกค้าที่มีชื่อผู้ใช้ตรงกับ `accountName`
            CustomersTable.select { CustomersTable.userName eq accountName }
                .mapNotNull {
                    // แปลงข้อมูลที่ดึงมาในแต่ละแถวเป็น CustomersField object
                    CustomersField(
                        it[CustomersTable.customerID],  // ดึงค่า customerID จากฐานข้อมูล
                        it[CustomersTable.userName],    // ดึงค่า userName จากฐานข้อมูล
                        it[CustomersTable.profileImage], // ดึงค่า profileImage จากฐานข้อมูล
                        it[CustomersTable.authKey],      // ดึงค่า authKey จากฐานข้อมูล
                        it[CustomersTable.timeStamp],    // ดึงค่า timeStamp จากฐานข้อมูล
                        it[CustomersTable.createAt],     // ดึงค่า createAt จากฐานข้อมูล
                        it[CustomersTable.personID]      // ดึงค่า personID จากฐานข้อมูล
                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }


    // เมธอดสำหรับเพิ่มข้อมูลลูกค้าใหม่
    suspend fun insert(customerData: CustomersForm): Boolean {
        return try {
            dbQuery {
                // เพิ่มข้อมูลลูกค้าใน CustomersTable
                CustomersTable.insert {
                    // กำหนดข้อมูลในคอลัมน์ต่างๆ
                    it[userName] = customerData.userName // กำหนดชื่อผู้ใช้ เพิ่มลงฐานข้อมูล
                    it[authKey] = customerData.authKey // กำหนด `Public Key` เพิ่มลงฐานข้อมูล
                    it[timeStamp] = getCurrentTime()  // กำหนดเวลาที่เพิ่มลงฐานข้อมูล
                    it[createAt] = getCurrentDate()   // กำหนดวันที่เพิ่มลงฐานข้อมูล
                }
            }
            true // สำเร็จ
        } catch (e: Exception) {
            // ปัญหาในการเพิ่มข้อมูล
            false
        }
    }


    // เมธอดสำหรับอัปเดตข้อมูลลูกค้า
    suspend fun update(customerID: Int, fieldName: String, newValue: String): Unit {
        return dbQuery {
            CustomersTable.update({ CustomersTable.customerID eq customerID }) {
                // กำหนดค่าใหม่ตามเมื่อเงื่อนไข ลงในคอลัมน์ที่ต้องการอัปเดต
                when (fieldName) {
                    userName.name -> {
                        // อัปเดต userName ให้กับลูกค้าที่มี Customer ID: $customerID
                        it[userName] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
                    }

                    profileImage.name -> {
                        // อัปเดต profileImage ให้กับลูกค้าที่มี Customer ID: $customerID
                        it[profileImage] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
                    }

                    authKey.name -> {
                        // อัปเดต authKey ให้กับลูกค้าที่มี Customer ID: $customerID
                        it[authKey] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
                    }

                    personID.name -> {
                        // อัปเดต personID ให้กับลูกค้าที่มี Customer ID: $customerID
                        // แปลง newValue เป็น Int ก่อน
                        it[personID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
                    }

                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }
        }
    }


    // เมธอดสำหรับลบข้อมูลลูกค้า
    suspend fun delete(id: Int): Boolean {
        return try {
            dbQuery {
                val rowsAffected: Int = CustomersTable.deleteWhere {
                    (CustomersTable.customerID eq id) //and
//                            (PeopleTable.personID eq id) and
//                            (AddressesTable.addressID eq id)
                }

                /**
                 * เมื่อใช้ฟังก์ชัน `deleteWhere` ใน Exposed Framework หรือคำสั่ง SQL
                 *
                 * @sample rowsAffected > 0: การลบสำเร็จและมีแถวถูกลบ, ข้อมูลถูกลบจากฐานข้อมูล.
                 * @sample rowsAffected == 0: ไม่มีข้อมูลที่ต้องการลบในฐานข้อมูล.
                 * @sample rowsAffected < 0: เกิดข้อผิดพลาดในกระบวนการลบ.
                 * */
                rowsAffected > 0 // ตรวจสอบว่ามีการลบข้อมูล (rowsAffected > 0) หรือไม่
            }
        } catch (e: Exception) {
            // หากเกิดข้อผิดพลาดในการลบข้อมูล
            false
        }
    }


}


// ฟังก์ชัน main สำหรับทดสอบการใช้งาน
suspend fun main() {

    // กำหนดค่าและเชื่อมต่อกับฐานข้อมูล
    DatabaseFactory.initialize()

    // ดึงค่า ID ที่มากที่สุดและค่า ID ที่น้อยที่สุด
    println("Min CustomerID: ${selectMinID()}")
    println("Max CustomerID: ${selectMaxID()}")


    // แสดงข้อมูลทั้งหมดของลูกค้า
    // สร้าง Coroutine Scope สำหรับรันการทำงานที่ต้องใช้ runBlocking
    // เลือกข้อมูลทั้งหมดของลูกค้า
    val customersList: List<CustomersField> = Customer.selectAllFromCustomers()

    // วนลูปผ่านลิสต์ของลูกค้าและแสดงข้อมูล
    customersList.forEach { E ->
        println("\n=====================\n")
        println("Customer ID: ${E.customerID}")
        println("User Name: ${E.userName}")
        println("Profile Image: ${E.profileImage}")
        println("Auth Key: ${E.authKey}")
        println("Time Stamp: ${E.timeStamp}")
        println("Create Account Date: ${E.createAt}")
        println("Person ID: ${E.personID}")
    }

//    // อัปเดตข้อมูล
//    val updateData = update(1, "ProfileImage", "lala.png")
//    println(updateData)
//
//    // ลบข้อมูล
//    val customerIDToDelete = delete(1)
//    println(customerIDToDelete)


    val userNameToSearch = "ทดสอบ 2"
    val foundCustomer = findCustomerByUserName(userNameToSearch)

    println("Found Customer by UserName \n'$userNameToSearch': \n${foundCustomer}")


}