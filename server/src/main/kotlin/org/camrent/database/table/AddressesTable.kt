package org.camrent.database.table

import org.camrent.database.table.PeopleTable.default
import org.camrent.database.table.StoresTable.uniqueIndex
import org.jetbrains.exposed.sql.Table

object AddressesTable : Table("Address") {

    val addressID = integer("AddressID").uniqueIndex()
    val locationName = text("LocationName").default("N/A")
    val city = text("City").default("N/A")
    val province = text("Province").default("N/A")
    val postalNumber = text("PostalNumber").default("N/A")
    val streetName = text("StreetName").default("N/A")

    override val primaryKey = PrimaryKey(addressID)
}