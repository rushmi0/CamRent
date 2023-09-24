package org.camrent.database.service


import com.CamRent.utils.Time.getCurrentDate
import com.CamRent.utils.Time.getCurrentTime
import org.camrent.database.DatabaseFactory
import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.forms.CustomersForm
import org.camrent.database.table.CustomersTable
import org.camrent.database.table.CustomersTable.authKey
import org.camrent.database.table.CustomersTable.customerID
import org.camrent.database.table.CustomersTable.personID
import org.camrent.database.table.CustomersTable.profileImage
import org.camrent.database.table.CustomersTable.userName
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
                    it[customerID],
                    it[userName],
                    it[profileImage],
                    it[authKey],
                    it[personID]
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


    transaction {
        // SELECT
        val result: Query = CustomersTable.selectAll()

        // SELECT WHERE
        val filteredResult = CustomersTable.select { customerID eq "CTM-00013" }.toList()

        // SELECT MAX
        val maxCustomerID = CustomersTable.slice(customerID.max()).selectAll()
            .single()[customerID.max()].toString()

        // SELECT MIN
        val minCustomerID = CustomersTable.slice(customerID.min()).selectAll()
            .single()[customerID.min()].toString()

        // SELECT SUM
        val sumOfCustomerIDs = CustomersTable.slice(customerID.sum()).selectAll()
            .single()[customerID.sum()].toString()

        // Print results
        println("SELECT All: $result")
        println("SELECT WHERE: $filteredResult")
        println("MAX CustomerID: $maxCustomerID")
        println("MIN CustomerID: $minCustomerID")
        println("SUM of CustomerIDs: $sumOfCustomerIDs")
    }
}