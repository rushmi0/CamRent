package org.camrent.database.table

import org.jetbrains.exposed.sql.Table

object FineTable : Table("Fine") {

    val fineID = integer("FineID")
    val description = text("Description")
    val image1 = text("Image1")
    val image2 = text("Image2")
    val image3 = text("Image3")
    val image4 = text("Image4")
    val penaltyFee = integer("PenaltyFee")
    val timeStamp = text("TimeStamp")
    val date = text("Date")
    val status = text("Status")
    val storeID = integer("StoreID").references(StoresTable.storeID)
    val productID = integer("ProductID").references(ProductsTable.productID)
    val customerID = integer("CustomerID").references(CustomersTable.customerID)

    override val primaryKey = PrimaryKey(fineID)
}