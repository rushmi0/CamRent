package org.camrent.routes.api.test


import java.io.File
import javax.imageio.ImageIO

fun displayImageFilesInDirectory(directoryPath: String, subDirectory: String) {
    val directory = File("$directoryPath/$subDirectory")

    if (directory.exists() && directory.isDirectory) {
        val files = directory.listFiles()

        if (files != null) {
            for (file in files) {
                if (isImageFile(file)) {
                    println("Image File: ${file.absolutePath}")
                }
            }
        }
    } else {
        println("Directory not found or is not a valid directory.")
    }
}

fun isImageFile(file: File): Boolean {
    return try {
        val image = ImageIO.read(file)
        image != null
    } catch (e: Exception) {
        false
    }
}

fun main() {
    val basePath = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images"
    val subDirectory = "preview"

    displayImageFilesInDirectory(basePath, subDirectory)
}
