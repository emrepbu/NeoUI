package com.pushforcestudio.neoui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

enum class NeoThemeMode { LIGHT, DARK, SYSTEM }

object NeoTheme {
    val colors: NeoColors
        @Composable get() = LocalNeoColors.current
    val dimens: NeoDimens
        @Composable get() = LocalNeoDimens.current
    val typography: NeoTypography
        @Composable get() = LocalNeoTypography.current
}

private fun standardLightColors() = NeoColors(
    surface = Color.White,
    text = Color.Black,
    border = Color.Black,
    shadow = Color.Black,
    error = Color(0xFFFF0000),
    secondary = Color(0xFF00E5FF),
)

private fun standardDarkColors() = NeoColors(
    surface = Color(0xFF1E1E1E),
    text = Color.White,
    border = Color.Black,
    shadow = Color.Black,
    error = Color(0xFFFF0000),
    secondary = Color(0xFF00E5FF),
)

@Composable
fun NeoTheme(
    colorScheme: NeoColorScheme = NeoColorScheme.Blue,
    themeMode: NeoThemeMode = NeoThemeMode.SYSTEM,
    dimens: NeoDimens = NeoDimens(),
    typography: NeoTypography = NeoTypography(),
    content: @Composable () -> Unit,
) {
    val isDark = when (themeMode) {
        NeoThemeMode.LIGHT -> false
        NeoThemeMode.DARK -> true
        NeoThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val schemeColors = if (isDark) colorScheme.dark else colorScheme.light
    val standard = if (isDark) standardDarkColors() else standardLightColors()

    val colors = schemeColors.copy(
        surface = standard.surface,
        text = standard.text,
        border = standard.border,
        shadow = standard.shadow,
        error = standard.error,
        secondary = standard.secondary,
    )

    CompositionLocalProvider(
        LocalNeoColors provides colors,
        LocalNeoDimens provides dimens,
        LocalNeoTypography provides typography,
    ) {
        content()
    }
}

@Composable
fun NeoTheme(
    colors: NeoColors,
    dimens: NeoDimens = NeoDimens(),
    typography: NeoTypography = NeoTypography(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalNeoColors provides colors,
        LocalNeoDimens provides dimens,
        LocalNeoTypography provides typography,
    ) {
        content()
    }
}
