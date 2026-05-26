package com.pushforcestudio.neoui.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.neoBrutalistStyle(
    shadowColor: Color = Color.Black,
    borderColor: Color = Color.Black,
    borderWidth: Dp = 4.dp,
    shadowOffsetX: Dp = 6.dp,
    shadowOffsetY: Dp = 6.dp,
    contentOffsetX: Dp = 0.dp,
    contentOffsetY: Dp = 0.dp,
    backgroundColor: Color = Color.White,
    maxShadowOffsetX: Dp = 10.dp,
    maxShadowOffsetY: Dp = 10.dp,
): Modifier = this
    .padding(end = maxShadowOffsetX, bottom = maxShadowOffsetY)
    .drawBehind {
        val shadowPxX = shadowOffsetX.toPx()
        val shadowPxY = shadowOffsetY.toPx()
        drawRoundRect(
            color = shadowColor,
            topLeft = Offset(shadowPxX, shadowPxY),
            cornerRadius = CornerRadius(0f, 0f),
            size = size,
        )
    }
    .offset(x = contentOffsetX, y = contentOffsetY)
    .background(color = backgroundColor)
    .border(width = borderWidth, color = borderColor)
