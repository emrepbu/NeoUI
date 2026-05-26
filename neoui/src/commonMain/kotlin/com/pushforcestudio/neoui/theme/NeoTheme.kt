package com.pushforcestudio.neoui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun NeoTheme(
    colors: NeoColors = NeoColors(),
    dimens: NeoDimens = NeoDimens(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalNeoColors provides colors,
        LocalNeoDimens provides dimens,
    ) {
        content()
    }
}
