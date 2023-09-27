package org.camrent.database.table

import org.jetbrains.exposed.sql.Table

object PeopleTable :  Table("People") {

    val personID = integer("PersonID")
    val firstName = text("FirstName")
    val lastName = text("LastName")
    val email = text("Email")
    val phoneNumber = text("PhoneNumber")
    val addressID = integer("AddressID").references(AddressesTable.addressID).nullable()

    override val primaryKey = PrimaryKey(personID)
}