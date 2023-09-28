package org.camrent.database.service


import com.CamRent.utils.Time.getCurrentDate
import com.CamRent.utils.Time.getCurrentTime
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.camrent.database.DatabaseFactory
import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.CustomersField
import org.camrent.database.forms.CustomersForm
import org.camrent.database.service.Customer.selectMaxID
import org.camrent.database.service.Customer.selectMinID
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
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.*


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

    data class CustomerName(val userName: String, val customerID: Int)

    suspend fun getCustomerNameById(customerId: Int): CustomerName? {
        return dbQuery {
            CustomersTable.select { CustomersTable.customerID eq customerId }
                .mapNotNull {
                    CustomerName(
                        it[CustomersTable.userName],
                        it[CustomersTable.customerID]
                    )
                }
                .singleOrNull()
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


    // เมธอดสำหรับเพิ่มข้อมูลลูกค้าใหม่
    suspend fun insert(customerData: CustomersForm) {
        // สร้าง customerID ใหม่
        val id = genKey()
        dbQuery {
            // เพิ่มข้อมูลลูกค้าใน CustomersTable
            CustomersTable.insert {
                // กำหนดข้อมูลในคอลัมน์ต่างๆ
                it[customerID] = id
                it[userName] = customerData.userName
                it[authKey] = customerData.authKey
                it[timeStamp] = getCurrentTime()
                it[createAt] = getCurrentDate()
                it[personID] = id // ค่า personID ต้องตรงกับ customerID
            }

            PeopleTable.insert {
                it[personID] = id
            }

            AddressesTable.insert {
                it[addressID] = id
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
    suspend fun delete(id: Int): Unit {
        dbQuery {
            // ลบข้อมูลลูกค้าที่มี customerID และ personID ตรงกับที่ระบุ
            CustomersTable.deleteWhere {
                (CustomersTable.customerID eq id) and
                        (PeopleTable.personID eq id) and
                        (AddressesTable.addressID eq id)
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

    val id = Customer.genKey()
    println(id)


    // แสดงข้อมูลทั้งหมดของลูกค้า
    // สร้าง Coroutine Scope สำหรับรันการทำงานที่ต้องใช้ runBlocking
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

//    // อัปเดตข้อมูล
//    val updateData = update(1, "ProfileImage", "lala.png")
//    println(updateData)
//
//    // ลบข้อมูล
//    val customerIDToDelete = delete(1)
//    println(customerIDToDelete)

}