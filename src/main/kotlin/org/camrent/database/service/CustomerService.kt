package org.camrent.database.service

import org.camrent.database.DatabaseFactory
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

import org.camrent.utils.Time.getCurrentDate
import org.camrent.utils.Time.getCurrentTime
import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.CustomersField
import org.camrent.database.forms.CustomersForm
import org.camrent.database.table.AddressesTable
import org.camrent.database.table.CustomersTable
import org.camrent.database.table.CustomersTable.authKey
import org.camrent.database.table.CustomersTable.createAt
import org.camrent.database.table.CustomersTable.customerID
import org.camrent.database.table.CustomersTable.personID
import org.camrent.database.table.CustomersTable.profileImage
import org.camrent.database.table.CustomersTable.timeStamp
import org.camrent.database.table.CustomersTable.userName
import org.camrent.database.table.PeopleTable


// สร้างอ็อบเจกต์ Customer สำหรับจัดการข้อมูลลูกค้า
object CustomerService {


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
            CustomersTable.select { customerID eq userID }
                .mapNotNull {
                    // แปลงข้อมูลที่ดึงมาในแต่ละแถวเป็น CustomersField object
                    CustomersField(
                        it[customerID],  // ดึงค่า customerID จากฐานข้อมูล
                        it[userName],    // ดึงค่า userName จากฐานข้อมูล
                        it[profileImage], // ดึงค่า profileImage จากฐานข้อมูล
                        it[authKey],      // ดึงค่า authKey จากฐานข้อมูล
                        it[timeStamp],    // ดึงค่า timeStamp จากฐานข้อมูล
                        it[createAt],     // ดึงค่า createAt จากฐานข้อมูล
                        it[personID]      // ดึงค่า personID จากฐานข้อมูล
                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }

    suspend fun findCustomerByUserName(accountName: String): CustomersField? {
        return dbQuery {
            // เลือกข้อมูลลูกค้าที่มีชื่อผู้ใช้ตรงกับ `accountName`
            CustomersTable.select { userName eq accountName }
                .mapNotNull {
                    // แปลงข้อมูลที่ดึงมาในแต่ละแถวเป็น CustomersField object
                    CustomersField(
                        it[customerID],  // ดึงค่า customerID จากฐานข้อมูล
                        it[userName],    // ดึงค่า userName จากฐานข้อมูล
                        it[profileImage], // ดึงค่า profileImage จากฐานข้อมูล
                        it[authKey],      // ดึงค่า authKey จากฐานข้อมูล
                        it[timeStamp],    // ดึงค่า timeStamp จากฐานข้อมูล
                        it[createAt],     // ดึงค่า createAt จากฐานข้อมูล
                        it[personID]      // ดึงค่า personID จากฐานข้อมูล
                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }


    // เมธอดสำหรับเพิ่มข้อมูลลูกค้าใหม่
    suspend fun insert(field: CustomersForm): Boolean {
        return try {
            dbQuery {
                // เพิ่มข้อมูลลูกค้าใน CustomersTable
                CustomersTable.insert {
                    // กำหนดข้อมูลในคอลัมน์ต่างๆ
                    it[userName] = field.userName // กำหนดชื่อผู้ใช้ เพิ่มลงฐานข้อมูล
                    it[authKey] = field.authKey // กำหนด `Public Key` เพิ่มลงฐานข้อมูล
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
    suspend fun update(customerID: Int, fieldName: String, newValue: String): Boolean {
        return dbQuery {
            val updatedRowCount = CustomersTable.update({ CustomersTable.customerID eq customerID }) {
                // กำหนดค่าใหม่ตามเมื่อเงื่อนไข ลงในคอลัมน์ที่ต้องการอัปเดต
                when (fieldName) {
                    "UserName" -> {
                        // อัปเดต userName ให้กับลูกค้าที่มี Customer ID: $customerID
                        it[userName] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
                    }

                    "ProfileImage" -> {
                        // อัปเดต profileImage ให้กับลูกค้าที่มี Customer ID: $customerID
                        it[profileImage] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
                    }

                    "AuthKey" -> {
                        // อัปเดต authKey ให้กับลูกค้าที่มี Customer ID: $customerID
                        it[authKey] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
                    }

                    "PersonID" -> {
                        // อัปเดต personID ให้กับลูกค้าที่มี Customer ID: $customerID
                        // แปลง newValue เป็น Int ก่อน
                        it[personID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
                    }

                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }
            updatedRowCount > 0 // คืนค่าเป็น true ถ้ามีการอัปเดตเรียบร้อย
        }
    }


    // เมธอดสำหรับลบข้อมูลลูกค้า
    suspend fun delete(id: Int): Boolean {
        return try {
            dbQuery {
                val customerDeleted = CustomersTable.deleteWhere { customerID eq id } > 0
                val personDeleted = PeopleTable.deleteWhere { personID eq id } > 0
                //val addressDeleted = AddressesTable.deleteWhere { addressID eq id } > 0
                return@dbQuery customerDeleted && personDeleted //&& addressDeleted
            }
        } catch (e: Exception) {
            println("จับข้อผิดพลาดและคืนค่า false: $e")
            false
        }
    }

}
