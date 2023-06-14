package com.diegonunez.chatkata.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


val WHITE200 = Color(0xFFe0e0e0)
val BLACK200 = Color(0xFA212020)
val GREEN450 = Color(0xFF1AA05B)

@Composable
fun getTitleColor(): Color {
    return if (isSystemInDarkTheme()) {
        WHITE200
    } else {
        BLACK200
    }
}