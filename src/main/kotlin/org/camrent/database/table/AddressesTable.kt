package org.camrent.database.table

import org.jetbrains.exposed.sql.Table

object AddressesTable : Table("Address") {

    val addressID = integer("AddressID")
    val locationName = text("LocationName")
    val city = text("City")
    val province = text("Province")
    val postalNumber = text("PostalNumber")
    val streetName = text("StreetName")

    override val primaryKey = PrimaryKey(addressID)
}