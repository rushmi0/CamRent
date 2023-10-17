package org.camrent.database.table

import org.camrent.database.table.StoresTable.uniqueIndex
import org.jetbrains.exposed.sql.Table

object PeopleTable :  Table("People") {

    val personID = integer("PersonID").uniqueIndex()
    val firstName = text("FirstName").default("N/A")
    val lastName = text("LastName").uniqueIndex().default("N/A")
    val email = text("Email").uniqueIndex().default("N/A")
    val phoneNumber = text("PhoneNumber").uniqueIndex().default("N/A")

    val addressID = integer("AddressID").references(AddressesTable.addressID).nullable()

    override val primaryKey = PrimaryKey(personID)
}