package com.pushforcestudio.neoui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class NeoDimens(
    val shadowOffset: Dp = 6.dp,
    val borderWidth: Dp = 4.dp,
    val cornerRadius: Dp = 0.dp,
)

val LocalNeoDimens = staticCompositionLocalOf { NeoDimens() }
