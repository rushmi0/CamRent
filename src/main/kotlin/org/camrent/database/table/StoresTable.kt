package org.camrent.database.table

import org.jetbrains.exposed.sql.Table

object StoresTable : Table("Stores") {

    val storeID = integer("StoreID")
    val storeName = text("StoreName")
    val profileImage = text("ProfileImage")
    val paymentMethod = text("PaymentMethod")
    val authKey = text("AuthKey")
    val timeStamp = text("TimeStamp")
    val createAt = text("CreateAccountDate")
    val personID = integer("PersonID").references(PeopleTable.personID).nullable()

    override val primaryKey = PrimaryKey(storeID)
}