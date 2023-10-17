package org.camrent.database.service


import org.camrent.database.DatabaseFactory.dbQuery

import org.camrent.database.field.TransactionsField
import org.camrent.database.forms.TransactionsForm

import org.camrent.database.table.TransactionsTable
import org.camrent.database.table.TransactionsTable.date
import org.camrent.database.table.TransactionsTable.orderID
import org.camrent.database.table.TransactionsTable.status
import org.camrent.database.table.TransactionsTable.timeStamp
import org.camrent.database.table.TransactionsTable.totalPrice

import org.camrent.database.table.TransactionsTable.txID
import org.jetbrains.exposed.sql.insert

import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update


object TransactionsService {
    suspend fun selectAllFromTransactions(): List<TransactionsField> {
        return dbQuery {
            // ดึงข้อมูลทั้งหมดจากตาราง `TransactionsTable` แล้วแปลงให้อยู่ในรูปของ `TransactionsField`
            TransactionsTable.selectAll().map {
                TransactionsField(
                    it[txID],
                    it[totalPrice],
                    it[status],
                    it[timeStamp],
                    it[date],
                    it[orderID]
                )
            }
        }
    }

    suspend fun findTransactionsBytxID(txsID: Int): TransactionsField? {
        return dbQuery {
            TransactionsTable.select { TransactionsTable.txID eq txsID }
                .mapNotNull {
                    TransactionsField(
                        it[txID],
                        it[totalPrice],
                        it[status],
                        it[timeStamp],
                        it[date],
                        it[orderID],
                    )

                }
                .singleOrNull()
        }
    }

    suspend fun insert(field: TransactionsForm): Boolean {
        return try {
            dbQuery {

                TransactionsTable.insert {

                    it[totalPrice] = field.totalPrice
                    it[status] = field.status

                }
            }
            true // สำเร็จ
        } catch (e: Exception) {
            // ปัญหาในการเพิ่มข้อมูล
            false
        }
    }

    suspend fun update(txsID: Int, fieldName: String, newValue: String): Boolean {
        return dbQuery {
            val updatedRowCount = TransactionsTable.update({ TransactionsTable.txID eq txsID }) {

                when (fieldName) {

                    "TotalPrice" -> {
                        it[totalPrice] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี tx ID: $txsID")

                    }

                    "Status" -> {
                        it[status] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี tx ID: $txsID")
                    }

                    "OrderID" -> {
                        it[orderID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี tx ID: $txsID")
                    }

                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }
            updatedRowCount > 0 // คืนค่าเป็น true ถ้ามีการอัปเดตเรียบร้อย
        }
    }

}