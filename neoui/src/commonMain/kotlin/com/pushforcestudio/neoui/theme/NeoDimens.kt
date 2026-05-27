package com.pushforcestudio.neoui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class NeoDimens(
    val borderWidth: Dp = 4.dp,
    val cornerRadius: Dp = 0.dp,
    val horizontalShadowOffset: Dp = 4.dp,
    val verticalShadowOffset: Dp = 4.dp,
    val paddingSmall: Dp = 8.dp,
    val paddingMedium: Dp = 16.dp,
    val paddingLarge: Dp = 24.dp,
)

val LocalNeoDimens = staticCompositionLocalOf { NeoDimens() }
