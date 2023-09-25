package org.camrent.database.table


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

object CustomersTable : Table("Customers") {

    // กำหนดคอลัมน์ของตาราง
    val customerID = text("CustomerID")  // รหัสลูกค้า
    val sequenceNumber = integer("SequenceNumber")
    val userName = text("UserName").uniqueIndex()  // ชื่อผู้ใช้
    val profileImage = text("ProfileImage")  // รูปโปรไฟล์
    val authKey = text("AuthKey")  // คีย์การยืนยันตัวตน
    val timeStamp = text("TimeStamp")
    val createAt = text("CreateAccountDate")

    val personID = text("PersonID").references(PeopleTable.personID).uniqueIndex()

    // กำหนด Primary Key
    override val primaryKey = PrimaryKey(customerID)
}
