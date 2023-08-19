package com.example.common.utils

import java.net.URL
import java.util.*

fun imageUrlToBase64(url: String): String? {
    return try {
        val imageUrl = URL(url)
        val imageBytes = imageUrl.readBytes()
        val encoder = Base64.getEncoder()
        val base64Image = encoder.encodeToString(imageBytes)
        base64Image
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
