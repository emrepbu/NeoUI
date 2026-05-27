package com.pushforcestudio.neoui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Composable
fun NeoProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    trackColor: Color = Color.Unspecified,
    fillColor: Color = Color.Unspecified,
    height: Dp = 24.dp,
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current
    val resolvedTrackColor = if (trackColor == Color.Unspecified) colors.background else trackColor
    val resolvedFillColor = if (fillColor == Color.Unspecified) colors.accent else fillColor
    val clampedProgress = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .neoBrutalistStyle(
                shadowColor = colors.shadow,
                borderColor = colors.border,
                borderWidth = dimens.borderWidth,
                shadowOffsetX = dimens.shadowOffset,
                shadowOffsetY = dimens.shadowOffset,
                backgroundColor = resolvedTrackColor,
                maxShadowOffsetX = dimens.shadowOffset,
                maxShadowOffsetY = dimens.shadowOffset,
            )
            .fillMaxWidth()
            .height(height),
    ) {
        if (clampedProgress > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(clampedProgress)
                    .background(color = resolvedFillColor)
                    .border(width = dimens.borderWidth, color = colors.border),
            )
        }
    }
}
