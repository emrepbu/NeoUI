package com.pushforcestudio.neoui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Immutable
class NeoCheckboxColors internal constructor(
    val checkedContainer: Color,
    val uncheckedContainer: Color,
    val disabledContainer: Color,
    val checkmarkColor: Color,
    val borderColor: Color,
    val shadowColor: Color,
    val disabledShadowColor: Color,
)

object NeoCheckboxDefaults {

    @Composable
    fun colors(): NeoCheckboxColors {
        val colors = LocalNeoColors.current
        return NeoCheckboxColors(
            checkedContainer = colors.primary,
            uncheckedContainer = colors.background,
            disabledContainer = Color(0xFFCCCCCC),
            checkmarkColor = colors.text,
            borderColor = colors.border,
            shadowColor = colors.shadow,
            disabledShadowColor = Color.Transparent,
        )
    }
}

@Composable
fun NeoCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: NeoCheckboxColors = NeoCheckboxDefaults.colors(),
    interactionSource: MutableInteractionSource? = null,
) {
    val dimens = LocalNeoDimens.current
    val internalInteractionSource = remember { MutableInteractionSource() }
    val resolvedInteractionSource = interactionSource ?: internalInteractionSource

    val isPressed by resolvedInteractionSource.collectIsPressedAsState()

    val contentOffsetX by animateDpAsState(
        targetValue = if (enabled && isPressed) dimens.horizontalShadowOffset else 0.dp,
    )
    val contentOffsetY by animateDpAsState(
        targetValue = if (enabled && isPressed) dimens.verticalShadowOffset else 0.dp,
    )

    val effectiveBackground = when {
        !enabled -> colors.disabledContainer
        checked -> colors.checkedContainer
        else -> colors.uncheckedContainer
    }

    val effectiveShadow = if (enabled) colors.shadowColor else colors.disabledShadowColor

    val crossAlpha by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 150),
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                translationX = contentOffsetX.toPx()
                translationY = contentOffsetY.toPx()
            }
            .neoBrutalistStyle(
                backgroundColor = effectiveBackground,
                shadowColor = effectiveShadow,
                borderColor = colors.borderColor,
            )
            .clickable(
                interactionSource = resolvedInteractionSource,
                indication = null,
                enabled = enabled,
                onClick = { onCheckedChange(!checked) },
            )
            .semantics {
                role = Role.Checkbox
                stateDescription = if (checked) "Checked" else "Not checked"
            },
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
                    color = colors.checkmarkColor.copy(alpha = crossAlpha),
                    start = Offset(inset, inset),
                    end = Offset(size.width - inset, size.height - inset),
                    strokeWidth = strokeWidth,
                )
                drawLine(
                    color = colors.checkmarkColor.copy(alpha = crossAlpha),
                    start = Offset(size.width - inset, inset),
                    end = Offset(inset, size.height - inset),
                    strokeWidth = strokeWidth,
                )
            }
        }
    }
}
