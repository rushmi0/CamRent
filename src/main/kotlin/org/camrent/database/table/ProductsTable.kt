package org.camrent.database.table

import org.jetbrains.exposed.sql.Table

object ProductsTable : Table("Products") {

    val productID = integer("ProductID").uniqueIndex()
    val productName = text("ProductName").default("N/A")
    val image1 = text("Image1").default("N/A")
    val image2 = text("Image2").default("N/A")
    val image3 = text("Image3").default("N/A")
    val image4 = text("Image4").default("N/A")
    val productType = text("Type").default("N/A")
    val productPrice = integer("Price").default(0)
    val specDetail = text("SpecDetail").default("N/A")
    val description = text("Description").default("N/A")
    val status = text("Status").default("Unavailable")

    val storeID = integer("StoreID").references(StoresTable.storeID).nullable()

    override val primaryKey = PrimaryKey(productID)
}