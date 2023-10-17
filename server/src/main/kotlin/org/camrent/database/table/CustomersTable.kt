package org.camrent.database.table


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

object CustomersTable : Table("Customers") {

    // กำหนดคอลัมน์ของตาราง
    val customerID = integer("CustomerID").uniqueIndex()
    val userName = text("UserName").uniqueIndex()
    val profileImage = text("ProfileImage").default("N/A")
    val authKey = text("AuthKey").default("N/A")
    val timeStamp = text("TimeStamp").default("N/A")
    val createAt = text("CreateAccountDate").default("N/A")

    val personID = integer("PersonID").references(PeopleTable.personID).uniqueIndex()

    // กำหนด Primary Key
    override val primaryKey = PrimaryKey(customerID)
}
