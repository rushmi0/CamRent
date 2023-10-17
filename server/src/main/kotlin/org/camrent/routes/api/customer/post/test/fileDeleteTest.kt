import java.io.File

fun deleteFile(filePath: String): Boolean {
    val fileToDelete = File(filePath)

    return try {
        if (!fileToDelete.exists() || !fileToDelete.isFile) {
            throw IllegalArgumentException("The specified file path is either invalid or does not exist.")
        }

        // ทำการลบไฟล์
        val deleted = fileToDelete.delete()

        if (deleted) {
            println("File $filePath has been successfully deleted.")
        } else {
            println("Failed to delete file: $filePath")
        }

        deleted
    } catch (e: Exception) {
        println("Error: ${e.message}")
        false
    }
}

fun main() {
    val filePathToDelete = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/account/customers/usr_1/profileImage/16.jpg"
    val success = deleteFile(filePathToDelete)
    println("Operation success: $success")
}
