package org.camrent.database.table

import org.camrent.database.table.PeopleTable.references
import org.jetbrains.exposed.sql.Table

object ProductsTable : Table("Products") {

    val productID = integer("ProductID")
    val productName = text("ProductName")
    val image1 = text("Image1")
    val image2 = text("Image2")
    val image3 = text("Image3")
    val image4 = text("Image4")
    val type = text("Type")
    val price = integer("Price")
    val specDetail = text("SpecDetail")
    val description = text("Description")
    val status = text("Status")
    val storeID = integer("StoreID").references(StoresTable.storeID).nullable()

    override val primaryKey = PrimaryKey(productID)
}