package org.camrent.routes.api.test

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.PreviewImage() {

    get("img/preview/img/1") {

        val img1 = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/1.jpg"
        val img2 = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/2.jpg"
        val img3 = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/3.jpg"
        val img4 = "/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/4.jpg"

        val previewImages = getPreviewImageUrls("/home/rushmi0/items/Store/ReactApp/Main/server/src/main/resources/images/preview/")
        println(previewImages)
        call.respond(previewImages)
    }

}

data class PreviewImg(
    val image1: String,
    val image2: String,
    val image3: String,
    val image4: String
)

fun buildImageUrl(baseUrl: String, fileName: String): String {
    val encodedFileName = java.net.URLEncoder.encode(fileName, "UTF-8")
    return "$baseUrl/$encodedFileName"
}

fun getPreviewImageUrls(directoryPath: String): PreviewImg {
    val imageFiles = File(directoryPath).listFiles()

    requireNotNull(imageFiles) { "No image files found in the directory" }
    require(imageFiles.size >= 4) { "At least 4 image files are required" }

    val baseUrl = "http://127.0.0.1:8080/api/v1/preview/img/1/preview"

    return PreviewImg(
        image1 = buildImageUrl(baseUrl, imageFiles[0].name),
        image2 = buildImageUrl(baseUrl, imageFiles[1].name),
        image3 = buildImageUrl(baseUrl, imageFiles[2].name),
        image4 = buildImageUrl(baseUrl, imageFiles[3].name)
    )
}
