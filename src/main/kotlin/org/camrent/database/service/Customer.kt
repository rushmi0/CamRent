package org.camrent.database.service


import com.CamRent.utils.Time.getCurrentDate
import com.CamRent.utils.Time.getCurrentTime
import kotlinx.coroutines.runBlocking
import org.camrent.database.DatabaseFactory
import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.CustomersField
import org.camrent.database.forms.CustomersForm
import org.camrent.database.gen.INITKEY
import org.camrent.database.service.Customer.delete
import org.camrent.database.service.Customer.genPrimaryKey
import org.camrent.database.service.Customer.maxSequenceNumber
import org.camrent.database.service.Customer.selectMinID
import org.camrent.database.service.Customer.update
import org.camrent.database.table.CustomersTable
import org.camrent.database.table.CustomersTable.authKey
import org.camrent.database.table.CustomersTable.createAt
import org.camrent.database.table.CustomersTable.customerID
import org.camrent.database.table.CustomersTable.personID
import org.camrent.database.table.CustomersTable.profileImage
import org.camrent.database.table.CustomersTable.timeStamp
import org.camrent.database.table.CustomersTable.userName
import org.camrent.utils.ShiftTo.ByteArrayToHex
import org.camrent.utils.ShiftTo.DectoLittleEndian
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.nio.ByteBuffer
import java.nio.ByteOrder

// สร้างอ็อบเจกต์ Customer สำหรับจัดการข้อมูลลูกค้า
object Customer {

    // เมธอดสำหรับสร้าง Primary Key ใหม่
    suspend fun genPrimaryKey(): String {
        // ตรวจสอบ ID ที่น้อยที่สุด (หากไม่มีข้อมูลให้สร้าง ID ใหม่)
        val minID = this.maxSequenceNumber()
        return if (minID == "ไม่มีข้อมูล") {
            INITKEY.MAIN["CTM"].toString()
        } else {
            // สร้าง ID ใหม่โดยเพิ่ม 1 จากค่า ID ที่มากที่สุด
            val maxID = this.selectMaxID()
            val prefix = maxID?.substring(0, 4) ?: ""
            val suffix = maxID?.substring(4) ?: ""
            prefix + (suffix.toInt() + 1).DectoLittleEndian(2)
        }
    }

    // เมธอดสำหรับเพิ่มข้อมูลลูกค้า
    suspend fun insert(form: CustomersForm) {
        val primaryKey = this.genPrimaryKey()
        dbQuery {
            CustomersTable.insert {
                // กำหนดข้อมูลในคอลัมน์ต่างๆ
                it[customerID] = primaryKey
                it[userName] = form.UserName
                it[profileImage] = form.ProfileImage ?: "N/A"
                it[authKey] = form.AuthKey
                it[timeStamp] = getCurrentTime()
                it[createAt] = getCurrentDate()
                it[personID] = form.PersonID
            }
        }
    }

    // เมธอดสำหรับอัปเดตข้อมูลลูกค้า
    suspend fun update(customerID: String, fieldName: String, newValue: String): Unit {
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
                        it[personID] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
                    }
                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }
        }
    }


    // เมธอดสำหรับลบข้อมูลลูกค้า
    suspend fun delete(customerID: String): Unit {
        dbQuery {
            // ลบข้อมูลลูกค้าที่มี customerID ตรงกับที่ระบุ
            CustomersTable.deleteWhere { CustomersTable.customerID eq customerID }
        }
    }


    // * เมธอดสำหรับดึงข้อมูลลูกค้าทั้งหมด
    suspend fun selectAll(): List<CustomersField> {
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


    suspend fun maxSequenceNumber(): String? {
        return dbQuery {
            // ดึงข้อมูล customerID โดยเรียงลำดับ sequenceNumber จากมากไปน้อย
            val maxSequence = CustomersTable
                .slice(CustomersTable.customerID)
                .selectAll()

                /**
                 * ในกรณีนี้เราใช้ `orderBy` เพื่อเรียงลำดับตาม `sequenceNumber` ในทิศทางจากมากไปน้อย (SortOrder.DESC)
                 * และใช้ limit(1) เพื่อจำกัดให้เรียกเฉพาะหนึ่งค่าที่มากที่สุด โดยจะใช้ singleOrNull()
                 * เพื่อดึงค่าเดียวหรือถ้าไม่มีข้อมูลจะได้เป็น null
                 * */
                .orderBy(CustomersTable.sequenceNumber, SortOrder.DESC)
                .limit(1)
                .singleOrNull()

            // ดึงข้อมูล customerID จาก maxSequenceCustomer
            val maxSequenceCustomerID = maxSequence?.get(CustomersTable.customerID)

            /**
             *  `dbQuery` เป็น Higher-order Function: dbQuery เป็นฟังก์ชันที่รับฟังก์ชันอื่นเป็นอาร์กิวเมนต์
             *  `return@dbQuery` จึงใช้ return@dbQuery เพื่อระบุว่าเราต้องการคืนค่าจาก dbQuery ในจุดนั้น
             *  `maxSequenceCustomerID` เป็นค่าที่เราต้องการให้คืน
             * */

            // คืนค่า maxSequenceCustomerID จากส่วนของ dbQuery และถ้าไม่พบข้อมูลให้คืนค่า "ไม่มีข้อมูล"
            return@dbQuery maxSequenceCustomerID ?: "ไม่มีข้อมูล"
        }
    }


    // เมธอดสำหรับดึงค่า ID ที่มากที่สุด
    suspend fun selectMaxID(): String? {
        return dbQuery {
            CustomersTable.slice(customerID.max())
                .selectAll()
                .singleOrNull()
                ?.let {
                    it[customerID.max()] ?: ""
                }
        }
    }

    // เมธอดสำหรับดึงค่า ID ที่น้อยที่สุด
    suspend fun selectMinID(): String {
        return dbQuery {
            CustomersTable.slice(customerID.min())
                .selectAll()
                .singleOrNull()
                ?.getOrNull(customerID.min())
                ?.toString() ?: "ไม่มีข้อมูล"
        }
    }
}


// ฟังก์ชัน main สำหรับทดสอบการใช้งาน
suspend fun main() {

    // กำหนดค่าและเชื่อมต่อกับฐานข้อมูล
    DatabaseFactory.initialize()

    // ดึงค่า ID ที่มากที่สุดและค่า ID ที่น้อยที่สุด
    val maxCustomerId = Customer.selectMaxID()
    println("Min ID: ${selectMinID()}")
    println("Max CustomerID: $maxCustomerId")
    println("New ID: ${genPrimaryKey()}")


    // แสดงข้อมูลทั้งหมดของลูกค้า
    // สร้าง Coroutine Scope สำหรับรันการทำงานที่ต้องใช้ runBlocking
    runBlocking {

        // เลือกข้อมูลทั้งหมดของลูกค้า
        val customersList: List<CustomersField> = Customer.selectAll()

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
    }


    // อัปเดตข้อมูล
    val updateData = update("CTM-00002", "ProfileImage", "lala.png")
    println(updateData)

    // ลบข้อมูล
    val customerIDToDelete = delete("CTM-00001")
    println(customerIDToDelete)

    println(maxSequenceNumber())


    val num = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(1).array().ByteArrayToHex()
    println(num)
}