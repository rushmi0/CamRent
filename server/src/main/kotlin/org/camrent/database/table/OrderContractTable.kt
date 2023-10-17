package org.camrent.database.table

import org.camrent.database.table.StoresTable.uniqueIndex
import org.jetbrains.exposed.sql.Table

object OrderContractTable : Table("OrderContract") {

    val orderID = integer("OrderID").uniqueIndex()
    val quantity = integer("Quantity").default(0)
    val duration = text("Duration").default("N/A")

    val customerID = integer("CustomerID").references(CustomersTable.customerID).nullable()
    val addressID = integer("AddressID").references(AddressesTable.addressID).nullable()
    val productID = integer("ProductID").references(PeopleTable.personID).nullable()

    override val primaryKey = PrimaryKey(orderID)
}