package org.camrent.database.table

import org.jetbrains.exposed.sql.Table

object PeopleTable :  Table("People") {

    val personID = text("PersonID")
    val firstName = text("FirstName")
    val lastName = text("LastName")
    val email = text("Email")
    val phoneNumber = text("PhoneNumber")
    val addressID = text("AddressID").references(AddressesTable.addressID).nullable()

    override val primaryKey = PrimaryKey(personID)
}