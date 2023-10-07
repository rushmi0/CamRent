package org.camrent.database.table

import org.jetbrains.exposed.sql.Table

object StoresTable : Table("Stores") {

    val storeID = integer("StoreID").uniqueIndex()
    val storeName = text("StoreName").uniqueIndex()
    val profileImage = text("ProfileImage").default("N/A")
    val paymentMethod = text("PaymentMethod").default("Lightning Network")
    val authKey = text("AuthKey").default("N/A")
    val timeStamp = text("TimeStamp").default("N/A")
    val createAt = text("CreateAccountDate").default("N/A")

    val personID = integer("PersonID").references(PeopleTable.personID).nullable()

    override val primaryKey = PrimaryKey(storeID)
}