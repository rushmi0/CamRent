package org.camrent.database.table

import org.jetbrains.exposed.sql.Table

object TransactionsTable : Table("Transactions") {

    val txID = integer("TxID")
    val totalPrice = integer("TotalPrice")
    val status = text("Status")
    val timeStamp = text("TimeStamp")
    val date = text("Date")
    val orderID = integer("OrderID").references(OrderContractTable.orderID).nullable()

   override val primaryKey = PrimaryKey(txID)
}