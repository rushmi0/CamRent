package org.camrent.database.service


import org.camrent.database.DatabaseFactory.dbQuery

import org.camrent.database.field.FineField
import org.camrent.database.forms.ProductsForm
import org.camrent.database.table.FineTable
import org.camrent.database.table.FineTable.customerID
import org.camrent.database.table.FineTable.date
import org.camrent.database.table.FineTable.description
import org.camrent.database.table.FineTable.fineID
import org.camrent.database.table.FineTable.image1
import org.camrent.database.table.FineTable.image2
import org.camrent.database.table.FineTable.image3
import org.camrent.database.table.FineTable.image4
import org.camrent.database.table.FineTable.penaltyFee
import org.camrent.database.table.FineTable.productID
import org.camrent.database.table.FineTable.status
import org.camrent.database.table.FineTable.storeID
import org.camrent.database.table.FineTable.timeStamp
import org.camrent.database.table.ProductsTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

import org.jetbrains.exposed.sql.selectAll

object FineService {

    suspend fun selectAllFromFine(): List<FineField> {
        return dbQuery {

            FineTable.selectAll().map {
                FineField(
                    it[fineID],
                    it[timeStamp],
                    it[date],
                    it[description],
                    it[image1],
                    it[image2],
                    it[image3],
                    it[image4],
                    it[penaltyFee],
                    it[status],
                    it[storeID],
                    it[productID],
                    it[customerID]

                )
            }
        }
    }

    suspend fun findProductsByProductsID(finesID: Int): FineField?{
        return dbQuery {
            FineTable.select {fineID eq finesID }
                .mapNotNull {
                    FineField(

                        it[fineID],
                        it[timeStamp],
                        it[date],
                        it[description],
                        it[image1],
                        it[image2],
                        it[image3],
                        it[image4],
                        it[penaltyFee],
                        it[status],
                        it[storeID],
                        it[productID],
                        it[customerID]

                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }

    suspend fun insert(field: ProductsForm): Boolean {
        return try {
            dbQuery {
                // เพิ่มข้อมูลลูกค้าใน CustomersTable
                ProductsTable.insert {
                    // กำหนดข้อมูลในคอลัมน์ต่างๆ
                    it[productName] = field.productName



                }
            }
            true // สำเร็จ
        } catch (e: Exception) {
            // ปัญหาในการเพิ่มข้อมูล
            false
        }
    }

}