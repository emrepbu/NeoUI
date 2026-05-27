package com.pushforcestudio.neoui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Immutable
data class NeoTypography(
    val headingWeight: FontWeight = FontWeight.Bold,
    val baseWeight: FontWeight = FontWeight.Medium,
    val headingFontFamily: FontFamily = FontFamily.Default,
    val baseFontFamily: FontFamily = FontFamily.Default,
)

val LocalNeoTypography = staticCompositionLocalOf { NeoTypography() }
