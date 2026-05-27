package com.pushforcestudio.neoui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontWeight

data class NeoTypography(
    val headingWeight: FontWeight = FontWeight.Bold,
    val baseWeight: FontWeight = FontWeight.Medium,
)

val LocalNeoTypography = staticCompositionLocalOf { NeoTypography() }
