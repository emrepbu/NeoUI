package com.pushforcestudio.neoui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Composable
fun NeoTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = {},
    backgroundColor: Color = Color.Unspecified,
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current
    val barBackground = if (backgroundColor == Color.Unspecified) colors.background else backgroundColor

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(barBackground)
            .drawBehind {
                val sw = dimens.borderWidth.toPx()
                drawLine(
                    color = colors.border,
                    start = Offset(0f, size.height - sw / 2f),
                    end = Offset(size.width, size.height - sw / 2f),
                    strokeWidth = sw,
                )
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.size(48.dp),
            contentAlignment = Alignment.Center,
        ) {
            navigationIcon()
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            title()
        }

        actions()
    }
}
