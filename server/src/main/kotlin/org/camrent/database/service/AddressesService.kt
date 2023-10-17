package org.camrent.database.service

import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.AddressesField
import org.camrent.database.forms.AddressesForm
import org.camrent.database.table.AddressesTable
import org.camrent.database.table.AddressesTable.addressID
import org.camrent.database.table.AddressesTable.city
import org.camrent.database.table.AddressesTable.locationName
import org.camrent.database.table.AddressesTable.postalNumber
import org.camrent.database.table.AddressesTable.province
import org.camrent.database.table.AddressesTable.streetName
import org.camrent.database.table.PeopleTable
import org.jetbrains.exposed.sql.*

object AddressesService {

    // เมธอดสำหรับดึงค่า ID ที่มากที่สุด
    suspend fun selectMaxID(): Int {
        return dbQuery {
            AddressesTable.slice(addressID.max())
                .selectAll()
                .singleOrNull()
                ?.getOrNull(addressID.max()) ?: 0
        }
    }

    // เมธอดสำหรับดึงค่า ID ที่น้อยที่สุด
    suspend fun selectMinID(): Int {
        return dbQuery {
            AddressesTable.slice(addressID.min())
                .selectAll()
                .singleOrNull()
                ?.getOrNull(addressID.min()) ?: 0
        }
    }

    suspend fun selectAllFromAddresses(): List<AddressesField> {
        return dbQuery {
            AddressesTable.selectAll().map {
                AddressesField(
                    it[addressID],
                    it[locationName],
                    it[city],
                    it[province],
                    it[postalNumber],
                    it[streetName]
                )
            }
        }
    }

    suspend fun findAddressesByUserID(addrID: Int): AddressesField? {
        return dbQuery {
            AddressesTable.select { addressID eq addrID }
                .mapNotNull {
                    AddressesField(
                        it[addressID],
                        it[locationName],
                        it[city],
                        it[province],
                        it[postalNumber],
                        it[streetName]
                    )
                }
                .singleOrNull()
        }
    }


    suspend fun insert(record: AddressesForm): Boolean {
        return try {
            dbQuery {
                AddressesTable.insert {
                    it[locationName] = record.LocationName
                    it[city] = record.City
                    it[province] = record.Province
                    it[postalNumber] = record.PostalNumber
                    it[streetName] = record.StreetName
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }


    suspend fun update(addrID: Int, fieldName: String, newValue: String): Boolean {
        return dbQuery {
            val updatedRowCount = AddressesTable.update({ addressID eq addrID }) {
                when (fieldName) {
                    "LocationName" -> {
                        it[locationName] = newValue
                        println("อัปเดต $fieldName ให้กับบุคคลที่มี Address ID: $addrID")
                    }

                    "City" -> {
                        it[city] = newValue
                        println("อัปเดต $fieldName ให้กับบุคคลที่มี Address ID: $addrID")
                    }

                    "Province" -> {
                        it[province] = newValue
                        println("อัปเดต $fieldName ให้กับบุคคลที่มี Address ID: $addrID")
                    }

                    "PostalNumber" -> {
                        it[postalNumber] = newValue
                        println("อัปเดต $fieldName ให้กับบุคคลที่มี Address ID: $addrID")
                    }

                    "StreetName" -> {
                        it[streetName] = newValue
                        println("อัปเดต $fieldName ให้กับบุคคลที่มี Address ID: $addrID")
                    }

                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }

            updatedRowCount > 0 // คืนค่าเป็น true ถ้ามีการอัปเดตเรียบร้อย
        }
    }

}