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


    // สร้าง Mutex สำหรับการล็อคการเข้าถึงตัวแปร id
    private val idLock = Mutex()

    // เมธอดสำหรับสร้าง Primary Key ใหม่
    suspend fun genKey(): Int {
        // ล็อคการเข้าถึงตัวแปร id
        return idLock.withLock {
            val num = Customer.selectMaxID()
            if (num == 0) {
                1
            } else {
                num + 1
            }
        }
    }


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
            // ดึงข้อมูลทั้งหมดจาก CustomersTable แล้วแปลงให้อยู่ในรูปของ CustomersField
            CustomersTable.selectAll().map {
                CustomersField(
                    it[customerID],
                    it[userName],
                    it[profileImage],
                    it[authKey],
                    it[timeStamp],
                    it[createAt],
                    it[personID]
                )
            }
        }
    }



    // เมธอดสำหรับเพิ่มข้อมูลลูกค้าใหม่
    suspend fun insert(customerData: CustomersForm): Boolean {
        return try {
            dbQuery {
                // เพิ่มข้อมูลลูกค้าใน CustomersTable
                CustomersTable.insert {
                    // กำหนดข้อมูลในคอลัมน์ต่างๆ
                    it[userName] = customerData.userName
                    it[authKey] = customerData.authKey
                    it[timeStamp] = getCurrentTime()
                    it[createAt] = getCurrentDate()
                }
            }
            true // สำเร็จ
        } catch (e: Exception) {
            // ปัญหาในการเพิ่มข้อมูล
            false
        }
    }


    suspend fun findCustomerByUserName(accountName: String): CustomersField? {
        return dbQuery {
            CustomersTable.select { CustomersTable.userName eq accountName }
                .mapNotNull {
                    CustomersField(
                        it[CustomersTable.customerID],
                        it[CustomersTable.userName],
                        it[CustomersTable.profileImage],
                        it[CustomersTable.authKey],
                        it[CustomersTable.timeStamp],
                        it[CustomersTable.createAt],
                        it[CustomersTable.personID]
                    )
                }
                .singleOrNull()
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