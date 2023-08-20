package com.example.api.dto

class PushSolumLedRequest(
    val labelCode: String,
    val color: Color,
    val duration: String,
    val patternId: Int = 0,
    val multiLed: Boolean = false,
)

enum class Color {
    RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE
}
