package org.camrent.utils.test

import org.camrent.utils.AccountDirectory
import java.io.File


fun TestCreateDirectory(typeAccount: String, directoryID: Int): Boolean {
    // กำหนดเส้นทางของไดเร็กทอรีหลัก
    val baseDirectory = "src/main/resources/images/account/$typeAccount/usr_$directoryID"

    return when {
        typeAccount == "customers" -> {
            // สร้างไดเร็กทอรี profileImage สำหรับลูกค้า
            File("$baseDirectory/profileImage").apply { mkdirs() }
            true // สร้างไดเร็กทอรีสำเร็จ
        }

        typeAccount == "stores" -> {
            // สร้างไดเร็กทอรี profileImage สำหรับร้านค้า
            File("$baseDirectory/profileImage").apply { mkdirs() }
            // สร้างไดเร็กทอรี products, camera, และ accessories สำหรับร้านค้า
            File("$baseDirectory/products/camera").apply { mkdirs() }
            File("$baseDirectory/products/accessories").apply { mkdirs() }
            true // สร้างไดเร็กทอรีสำเร็จ
        }

        else -> false // ไม่สามารถสร้างไดเร็กทอรี เนื่องจากไม่รู้จักประเภทบัญชี
    }
}

fun main() {

    val targetID = 3

//    val dirCustomer = TestCreateDirectory("customers", targetID)
//    println(dirCustomer)

    val dirStores = TestCreateDirectory("stores", targetID)
    println(dirStores)

//    val id = 0
//
//    val customersPath = "customers"
//    AccountDirectory.createDirectory(customersPath, id)
//
//    val storesPath = "stores"
//    AccountDirectory.createDirectory(storesPath, id)
//
//    val target = 0
//    AccountDirectory.deleteDirectory(customersPath, target)
//    AccountDirectory.deleteDirectory(storesPath, target)
}
