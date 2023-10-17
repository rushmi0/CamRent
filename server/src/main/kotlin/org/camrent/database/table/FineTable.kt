package org.camrent.database.table

import org.camrent.database.table.StoresTable.uniqueIndex
import org.jetbrains.exposed.sql.Table

object FineTable : Table("Fine") {

    val fineID = integer("FineID").uniqueIndex()
    val description = text("Description").default("N/A")
    val image1 = text("Image1").default("N/A")
    val image2 = text("Image2").default("N/A")
    val image3 = text("Image3").default("N/A")
    val image4 = text("Image4").default("N/A")
    val penaltyFee = integer("PenaltyFee").default(0)
    val timeStamp = text("TimeStamp").default("N/A")
    val date = text("Date").default("N/A")
    val status = text("Status").default("Pending")

    val storeID = integer("StoreID").references(StoresTable.storeID)
    val productID = integer("ProductID").references(ProductsTable.productID)
    val customerID = integer("CustomerID").references(CustomersTable.customerID)

    override val primaryKey = PrimaryKey(fineID)
}