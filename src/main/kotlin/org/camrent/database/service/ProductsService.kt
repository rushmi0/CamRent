package org.camrent.database.service


import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.ProductsField
import org.camrent.database.forms.ProductsForm

import org.camrent.database.table.ProductsTable
import org.camrent.database.table.ProductsTable.description
import org.camrent.database.table.ProductsTable.image1
import org.camrent.database.table.ProductsTable.image2
import org.camrent.database.table.ProductsTable.image3
import org.camrent.database.table.ProductsTable.image4
import org.camrent.database.table.ProductsTable.price
import org.camrent.database.table.ProductsTable.productID
import org.camrent.database.table.ProductsTable.productName
import org.camrent.database.table.ProductsTable.specDetail
import org.camrent.database.table.ProductsTable.status
import org.camrent.database.table.ProductsTable.storeID
import org.camrent.database.table.ProductsTable.type
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

object ProductsService {


    // * เมธอดสำหรับดึงข้อมูลลูกค้าทั้งหมด
    suspend fun selectAllFromProducts(): List<ProductsField> {
        return dbQuery {
            // ดึงข้อมูลทั้งหมดจากตาราง `ProductsTable` แล้วแปลงให้อยู่ในรูปของ `ProductsField`
            ProductsTable.selectAll().map {
                ProductsField(
                    it[productID],
                    it[productName],
                    it[image1],
                    it[image2],
                    it[image3],
                    it[image4],
                    it[type],
                    it[price],
                    it[specDetail],
                    it[description],
                    it[status],
                    it[storeID]!!
                )
            }
        }
    }


    suspend fun findProductsByProductsID(productsID: Int): ProductsField?{
        return dbQuery {
            ProductsTable.select { productID eq productsID }
                .mapNotNull {
                    ProductsField(

                        it[productID],
                        it[productName],
                        it[image1],
                        it[image2],
                        it[image3],
                        it[image4],
                        it[type],
                        it[price],
                        it[specDetail],
                        it[description],
                        it[status],
                        it[storeID]!!

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

    suspend fun update(productsID: Int, fieldName: String, newValue: String): Boolean {
        return dbQuery {
            val updatedRowCount = ProductsTable.update({ productID eq productsID }) {

                when (fieldName) {

                    "ProductName" -> {
                        // อัปเดต profileImage ให้กับลูกค้าที่มี Customer ID: $customerID
                        it[productName] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Product ID: $productsID")
                    }

                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }
            updatedRowCount > 0 // คืนค่าเป็น true ถ้ามีการอัปเดตเรียบร้อย
        }
    }

}