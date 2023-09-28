package org.camrent.database.service


import org.camrent.database.DatabaseFactory
import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.PeopleField
import org.camrent.database.forms.PeopleForm
import org.camrent.database.table.PeopleTable
import org.camrent.database.table.PeopleTable.addressID
import org.camrent.database.table.PeopleTable.email
import org.camrent.database.table.PeopleTable.firstName
import org.camrent.database.table.PeopleTable.lastName
import org.camrent.database.table.PeopleTable.personID
import org.camrent.database.table.PeopleTable.phoneNumber
import org.jetbrains.exposed.sql.*

object People {


    // เมธอดสำหรับดึงค่า ID ที่มากที่สุด
    suspend fun selectMaxID(): Int {
        return DatabaseFactory.dbQuery {
            PeopleTable.slice(PeopleTable.personID.max())
                .selectAll()
                .singleOrNull()
                ?.getOrNull(PeopleTable.personID.max()) ?: 0
        }
    }

    // เมธอดสำหรับดึงค่า ID ที่น้อยที่สุด
    suspend fun selectMinID(): Int {
        return DatabaseFactory.dbQuery {
            PeopleTable.slice(PeopleTable.personID.min())
                .selectAll()
                .singleOrNull()
                ?.getOrNull(PeopleTable.personID.min()) ?: 0
        }
    }


    suspend fun selectAll(): List<PeopleField> {
        return dbQuery {
            // ดึงข้อมูลทั้งหมดจาก CustomersTable แล้วแปลงให้อยู่ในรูปของ CustomersField
            PeopleTable.selectAll().map {
                PeopleField(
                    it[personID],
                    it[firstName],
                    it[lastName],
                    it[email],
                    it[phoneNumber],
                    it[addressID]
                )
            }
        }
    }


    suspend fun insert(id: Int, peopleData: PeopleForm) {

        dbQuery {
            // เพิ่มข้อมูลบุคคลใน PeopleTable
            PeopleTable.insert {
                // กำหนดข้อมูลในคอลัมน์ต่างๆ
                it[personID] = id
                it[firstName] = peopleData.firstName
                it[lastName] = peopleData.lastName
                it[email] = peopleData.email
                it[phoneNumber] = peopleData.phoneNumber
                it[addressID] = id
            }
        }
    }


    suspend fun update(personID: Int, fieldName: String, newValue: String): Unit {
        return dbQuery {
            PeopleTable.update({ PeopleTable.personID eq personID })
            {
                when (fieldName) {
                    firstName.name -> {
                        it[firstName] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $personID")
                    }

                    lastName.name -> {
                        it[lastName] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $personID")
                    }

                    email.name -> {
                        it[email] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $personID")
                    }

                    phoneNumber.name -> {
                        it[phoneNumber] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $personID")
                    }

                }
            }
        }
    }


}