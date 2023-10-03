package org.camrent.database.table

import org.jetbrains.exposed.sql.Table

object OrderContractTable : Table("OrderContract") {

    val orderID = integer("OrderID")
    val quantity = integer("Quantity")
    val duration = text("Duration")
    val customerID = integer("CustomerID").references(CustomersTable.customerID).nullable()
    val addressID = integer("AddressID").references(AddressesTable.addressID).nullable()
    val productID = integer("ProductID").references(PeopleTable.personID).nullable()

    override val primaryKey = PrimaryKey(orderID)
}