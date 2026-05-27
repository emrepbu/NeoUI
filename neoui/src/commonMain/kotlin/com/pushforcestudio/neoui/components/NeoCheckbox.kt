package com.pushforcestudio.neoui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Composable
fun NeoCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current
    val internalInteractionSource = remember { MutableInteractionSource() }
    val resolvedInteractionSource = interactionSource ?: internalInteractionSource

    val isPressed by resolvedInteractionSource.collectIsPressedAsState()

    val contentOffsetX by animateDpAsState(
        targetValue = if (isPressed) dimens.horizontalShadowOffset else 0.dp,
    )
    val contentOffsetY by animateDpAsState(
        targetValue = if (isPressed) dimens.verticalShadowOffset else 0.dp,
    )

    val background = if (checked) colors.primary else colors.background

    val crossAlpha by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 150),
    )

    Box(
        modifier = modifier
            .neoBrutalistStyle(backgroundColor = background)
            .offset(x = contentOffsetX, y = contentOffsetY)
            .clickable(
                interactionSource = resolvedInteractionSource,
                indication = null,
                enabled = enabled,
                onClick = { onCheckedChange(!checked) },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .size(34.dp)
                .padding(4.dp),
        ) {
            if (crossAlpha > 0f) {
                val strokeWidth = 4.dp.toPx()
                val inset = strokeWidth
                drawLine(
                    color = colors.text.copy(alpha = crossAlpha),
                    start = Offset(inset, inset),
                    end = Offset(size.width - inset, size.height - inset),
                    strokeWidth = strokeWidth,
                )
                drawLine(
                    color = colors.text.copy(alpha = crossAlpha),
                    start = Offset(size.width - inset, inset),
                    end = Offset(inset, size.height - inset),
                    strokeWidth = strokeWidth,
                )
            }
        }
    }
}
