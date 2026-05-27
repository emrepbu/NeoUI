package com.pushforcestudio.neoui.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Composable
fun Modifier.neoBrutalistStyle(
    shadowColor: Color = Color.Unspecified,
    borderColor: Color = Color.Unspecified,
    borderWidth: Dp? = null,
    cornerRadius: Dp? = null,
    shadowOffsetX: Dp? = null,
    shadowOffsetY: Dp? = null,
    backgroundColor: Color = Color.Unspecified,
): Modifier {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current

    val resolvedShadowColor = if (shadowColor == Color.Unspecified) colors.shadow else shadowColor
    val resolvedBorderColor = if (borderColor == Color.Unspecified) colors.border else borderColor
    val resolvedBorderWidth = borderWidth ?: dimens.borderWidth
    val resolvedCornerRadius = cornerRadius ?: dimens.cornerRadius
    val resolvedShadowOffsetX = shadowOffsetX ?: dimens.horizontalShadowOffset
    val resolvedShadowOffsetY = shadowOffsetY ?: dimens.verticalShadowOffset
    val resolvedBackgroundColor = if (backgroundColor == Color.Unspecified) colors.background else backgroundColor

    val paddingEnd = resolvedShadowOffsetX.coerceAtLeast(0.dp)
    val paddingBottom = resolvedShadowOffsetY.coerceAtLeast(0.dp)
    val paddingStart = (-resolvedShadowOffsetX).coerceAtLeast(0.dp)
    val paddingTop = (-resolvedShadowOffsetY).coerceAtLeast(0.dp)

    return this
        .padding(start = paddingStart, top = paddingTop, end = paddingEnd, bottom = paddingBottom)
        .drawBehind {
            val cornerRadiusPx = resolvedCornerRadius.toPx()
            val shadowPxX = resolvedShadowOffsetX.toPx()
            val shadowPxY = resolvedShadowOffsetY.toPx()

            if (resolvedShadowOffsetX != 0.dp || resolvedShadowOffsetY != 0.dp) {
                drawRoundRect(
                    color = resolvedShadowColor,
                    topLeft = Offset(shadowPxX, shadowPxY),
                    cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx),
                    size = size,
                )
            }
        }
        .background(color = resolvedBackgroundColor)
        .border(width = resolvedBorderWidth, color = resolvedBorderColor)
}
