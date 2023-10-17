package org.camrent.database.table


import org.camrent.database.table.StoresTable.uniqueIndex
import org.jetbrains.exposed.sql.Table

object TransactionsTable : Table("Transactions") {

    val txID = integer("TxID").uniqueIndex()
    val totalPrice = integer("TotalPrice").default(0)
    val status = text("Status").default("Pending") //  'Pending'  / 'Confirmed'
    val timeStamp = text("TimeStamp").default("N/A")
    val date = text("Date").default("N/A")

    val orderID = integer("OrderID").references(OrderContractTable.orderID).nullable()

   override val primaryKey = PrimaryKey(txID)
}