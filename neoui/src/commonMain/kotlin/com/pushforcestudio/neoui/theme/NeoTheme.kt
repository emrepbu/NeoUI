package com.pushforcestudio.neoui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

enum class NeoThemeMode { LIGHT, DARK, SYSTEM }

fun lightNeoColors() = NeoColors()

fun darkNeoColors() = NeoColors(
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    foreground = Color.White,
    text = Color.White,
    shadow = Color.White,
    border = Color.White,
    accent = Color(0xFFFF1493),
    primary = Color(0xFFFF1493),
    secondary = Color(0xFF00E5FF),
    error = Color(0xFFFF6666),
)

object NeoTheme {
    val colors: NeoColors
        @Composable get() = LocalNeoColors.current
    val dimens: NeoDimens
        @Composable get() = LocalNeoDimens.current
}

@Composable
fun NeoTheme(
    colors: NeoColors,
    dimens: NeoDimens = NeoDimens(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalNeoColors provides colors,
        LocalNeoDimens provides dimens,
    ) {
        content()
    }
}

@Composable
fun NeoTheme(
    themeMode: NeoThemeMode = NeoThemeMode.SYSTEM,
    dimens: NeoDimens = NeoDimens(),
    content: @Composable () -> Unit,
) {
    val isDark = when (themeMode) {
        NeoThemeMode.LIGHT -> false
        NeoThemeMode.DARK -> true
        NeoThemeMode.SYSTEM -> isSystemInDarkTheme()
    }
    val colors = if (isDark) darkNeoColors() else lightNeoColors()

    NeoTheme(colors = colors, dimens = dimens, content = content)
}
