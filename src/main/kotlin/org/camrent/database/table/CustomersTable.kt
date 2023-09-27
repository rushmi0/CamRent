package org.camrent.database.table


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

object CustomersTable : Table("Customers") {

    // กำหนดคอลัมน์ของตาราง
    val customerID = integer("CustomerID").uniqueIndex()  // รหัสลูกค้า
    val userName = text("UserName").uniqueIndex()  // ชื่อผู้ใช้
    val profileImage = text("ProfileImage")  // รูปโปรไฟล์
    val authKey = text("AuthKey")  // คีย์การยืนยันตัวตน
    val timeStamp = text("TimeStamp")
    val createAt = text("CreateAccountDate")

    val personID = integer("PersonID").references(PeopleTable.personID).uniqueIndex()

    // กำหนด Primary Key
    override val primaryKey = PrimaryKey(customerID)
}
