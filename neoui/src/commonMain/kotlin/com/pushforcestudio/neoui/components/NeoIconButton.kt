package com.pushforcestudio.neoui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NeoIconButton(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: NeoButtonColors = NeoButtonDefaults.filledColors(),
    shadows: NeoButtonShadows = NeoButtonDefaults.filledShadows(),
    interactionSource: MutableInteractionSource? = null,
    size: Dp = 48.dp,
    iconSize: Dp = 24.dp,
    tint: Color = Color.Unspecified,
) {
    NeoButtonCore(
        onClick = onClick,
        modifier = modifier.size(size),
        enabled = enabled,
        colors = colors,
        shadows = shadows,
        interactionSource = interactionSource,
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
    ) {
        Box(
            modifier = Modifier.size(size),
            contentAlignment = Alignment.Center,
        ) {
            NeoIcon(
                painter = painter,
                contentDescription = contentDescription,
                size = iconSize,
                tint = if (tint == Color.Unspecified) LocalContentColor.current else tint,
            )
        }
    }
}
