package com.pushforcestudio.neoui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.theme.LocalNeoColors

@Composable
fun NeoIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
    size: Dp = 24.dp,
) {
    val colors = LocalNeoColors.current
    val resolvedTint = if (tint == Color.Unspecified) colors.text else tint

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(size),
            colorFilter = ColorFilter.tint(resolvedTint),
        )
    }
}
