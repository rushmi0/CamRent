package org.camrent.database.service


import kotlinx.coroutines.runBlocking
import com.CamRent.utils.Time.getCurrentDate
import com.CamRent.utils.Time.getCurrentTime
import io.ktor.server.routing.*
import org.camrent.database.DatabaseFactory
import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.CustomersField
import org.camrent.database.forms.CustomersForm
import org.camrent.database.service.Customer.delete
import org.camrent.database.service.Customer.selectMaxID
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
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.*


// สร้างอ็อบเจกต์ Customer สำหรับจัดการข้อมูลลูกค้า
object Customer {

    // เมธอดสำหรับสร้าง Primary Key ใหม่
    private suspend fun genKey(): Int {
        val num = Customer.selectMaxID() ?: 0 // ถ้าเป็น null ให้คืนค่าเป็น 0
        return if (num == 0) {
            1
        } else {
            num + 1
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



    // เมธอดสำหรับเพิ่มข้อมูลลูกค้าใหม่
    suspend fun insert(form: CustomersForm) {
        val id = this.genKey()
        dbQuery {
            CustomersTable.insert {
                // กำหนดข้อมูลในคอลัมน์ต่างๆ
                it[customerID] = id
                it[userName] = form.UserName
                //it[profileImage] = form.ProfileImage
                it[authKey] = form.AuthKey
                it[timeStamp] = getCurrentTime()
                it[createAt] = getCurrentDate()
                it[personID] = id // ค่า personID ต้องตรงกับ customerID
            }
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
    suspend fun delete(customerID: Int): Unit {
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
//    runBlocking {
//
//        // เลือกข้อมูลทั้งหมดของลูกค้า
//        val customersList: List<CustomersField> = Customer.selectAll()
//
//        // วนลูปผ่านลิสต์ของลูกค้าและแสดงข้อมูล
//        customersList.forEach { E ->
//            println("\n=====================\n")
//            println("Customer ID: ${E.customerID}")
//            println("User Name: ${E.userName}")
//            println("Profile Image: ${E.profileImage}")
//            println("Auth Key: ${E.authKey}")
//            println("Time Stamp: ${E.timeStamp}")
//            println("Create Account Date: ${E.createAt}")
//            println("Person ID: ${E.personID}")
//        }
//    }


//    // อัปเดตข้อมูล
//    val updateData = update(1, "ProfileImage", "lala.png")
//    println(updateData)
//
//    // ลบข้อมูล
//    val customerIDToDelete = delete(1)
//    println(customerIDToDelete)

}