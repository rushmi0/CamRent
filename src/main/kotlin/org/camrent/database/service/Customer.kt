package org.camrent.database.service


import com.CamRent.utils.Time.getCurrentDate
import com.CamRent.utils.Time.getCurrentTime
import org.camrent.database.DatabaseFactory
import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.forms.CustomersForm
import org.camrent.database.table.CustomersTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


object Customer {


    suspend fun insert(form: CustomersForm) {
        dbQuery {
            CustomersTable.insert {
                it[customerID] = form.CustomerID
                it[userName] = form.UserName
                it[profileImage] = form.ProfileImage ?: ""
                it[authKey] = form.AuthKey
                it[timeStamp] = getCurrentTime()
                it[createAt] = getCurrentDate()
                it[personID] = form.PersonID
            }
        }
    }

    suspend fun selectAll(): List<CustomersForm> {
        return dbQuery {
            CustomersTable.selectAll().map {
                CustomersForm(
                    it[CustomersTable.customerID],
                    it[CustomersTable.userName],
                    it[CustomersTable.profileImage],
                    it[CustomersTable.authKey],
                    it[CustomersTable.personID]
                )
            }
        }
    }


    suspend fun selectMaxID(): String? {
        return dbQuery {
            CustomersTable.slice(CustomersTable.customerID.max())
                .selectAll()
                .singleOrNull()
                ?.let {
                    it[CustomersTable.customerID.max()] ?: ""
                }
        }
    }

    fun String.stringToSignedIntArray(): ByteArray {
        val byteList = mutableListOf<Byte>()
        for (char in this) {
            byteList.add(char.toByte())
        }
        return byteList.toByteArray()
    }


}

suspend fun main() {

    DatabaseFactory.initialize()
//    val dataAll = selectAll()
//    println(dataAll)
//
//    val maxCustomerId = Customer.selectMaxID()
//    println("Max CustomerID: $maxCustomerId")
//
//    val idBytes = maxCustomerId?.stringToSignedIntArray()
//    println(Arrays.toString(idBytes))

    // ต่อสายการทำงาน (transaction)
    transaction {
        // SELECT
        val result: Query = CustomersTable.selectAll()

        // SELECT WHERE
        val filteredResult = CustomersTable.select { CustomersTable.customerID eq "CTM-00013" }.toList()

        // SELECT MAX
        val maxCustomerID = CustomersTable.slice(CustomersTable.customerID.max()).selectAll()
            .single()[CustomersTable.customerID.max()].toString()

        // SELECT MIN
        val minCustomerID = CustomersTable.slice(CustomersTable.customerID.min()).selectAll()
            .single()[CustomersTable.customerID.min()].toString()

        // SELECT SUM
        val sumOfCustomerIDs = CustomersTable.slice(CustomersTable.customerID.sum()).selectAll()
            .single()[CustomersTable.customerID.sum()].toString()

        // Print results
        println("SELECT All: $result")
        println("SELECT WHERE: $filteredResult")
        println("MAX CustomerID: $maxCustomerID")
        println("MIN CustomerID: $minCustomerID")
        println("SUM of CustomerIDs: $sumOfCustomerIDs")
    }
}