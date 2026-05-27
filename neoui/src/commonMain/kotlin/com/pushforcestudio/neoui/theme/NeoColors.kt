package com.pushforcestudio.neoui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class NeoColors(
    val background: Color = Color.White,
    val surface: Color = Color.White,
    val text: Color = Color.Black,
    val primary: Color = Color(0xFFFF1493),
    val secondary: Color = Color(0xFF00E5FF),
    val border: Color = Color.Black,
    val shadow: Color = Color.Black,
    val error: Color = Color(0xFFFF0000),
)

val LocalNeoColors = staticCompositionLocalOf { NeoColors() }
