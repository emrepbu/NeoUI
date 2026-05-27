package com.pushforcestudio.neoui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Immutable
class NeoButtonColors internal constructor(
    private val normalContainer: Color,
    private val pressedContainer: Color,
    private val disabledContainer: Color,
    private val normalContent: Color,
    private val disabledContent: Color,
    private val normalBorder: Color,
    private val disabledBorder: Color,
    private val normalShadow: Color,
    private val disabledShadow: Color,
) {
    fun containerColor(enabled: Boolean, isPressed: Boolean): Color = when {
        !enabled -> disabledContainer
        isPressed -> pressedContainer
        else -> normalContainer
    }

    fun contentColor(enabled: Boolean): Color =
        if (enabled) normalContent else disabledContent

    fun borderColor(enabled: Boolean): Color =
        if (enabled) normalBorder else disabledBorder

    fun shadowColor(enabled: Boolean): Color =
        if (enabled) normalShadow else disabledShadow
}

@Immutable
class NeoButtonShadows internal constructor(
    private val normalOffsetX: Dp,
    private val normalOffsetY: Dp,
    private val disabledOffsetX: Dp,
    private val disabledOffsetY: Dp,
) {
    fun offsetX(enabled: Boolean): Dp =
        if (enabled) normalOffsetX else disabledOffsetX

    fun offsetY(enabled: Boolean): Dp =
        if (enabled) normalOffsetY else disabledOffsetY
}

val LocalContentColor = staticCompositionLocalOf { Color.Black }
val LocalNeoTextStyle = staticCompositionLocalOf { TextStyle.Default }

object NeoButtonDefaults {

    val ContentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)

    @Composable
    fun filledColors(): NeoButtonColors {
        val colors = LocalNeoColors.current
        val disabledGray = Color(0xFFCCCCCC)
        return NeoButtonColors(
            normalContainer = colors.background,
            pressedContainer = colors.primary,
            disabledContainer = disabledGray,
            normalContent = colors.text,
            disabledContent = colors.text.copy(alpha = 0.4f),
            normalBorder = colors.border,
            disabledBorder = colors.border.copy(alpha = 0.3f),
            normalShadow = colors.shadow,
            disabledShadow = Color.Transparent,
        )
    }

    @Composable
    fun outlinedColors(): NeoButtonColors {
        val colors = LocalNeoColors.current
        return NeoButtonColors(
            normalContainer = Color.Transparent,
            pressedContainer = Color.Transparent,
            disabledContainer = Color.Transparent,
            normalContent = colors.text,
            disabledContent = colors.text.copy(alpha = 0.4f),
            normalBorder = colors.border,
            disabledBorder = colors.border.copy(alpha = 0.3f),
            normalShadow = colors.shadow,
            disabledShadow = Color.Transparent,
        )
    }

    @Composable
    fun secondaryColors(): NeoButtonColors {
        val colors = LocalNeoColors.current
        val disabledGray = Color(0xFFCCCCCC)
        return NeoButtonColors(
            normalContainer = colors.surface,
            pressedContainer = colors.surface,
            disabledContainer = disabledGray,
            normalContent = colors.text,
            disabledContent = colors.text.copy(alpha = 0.4f),
            normalBorder = colors.border,
            disabledBorder = colors.border.copy(alpha = 0.3f),
            normalShadow = colors.shadow,
            disabledShadow = Color.Transparent,
        )
    }

    @Composable
    fun textColors(): NeoButtonColors {
        val colors = LocalNeoColors.current
        return NeoButtonColors(
            normalContainer = Color.Transparent,
            pressedContainer = Color.Transparent,
            disabledContainer = Color.Transparent,
            normalContent = colors.text,
            disabledContent = colors.text.copy(alpha = 0.4f),
            normalBorder = Color.Transparent,
            disabledBorder = Color.Transparent,
            normalShadow = Color.Transparent,
            disabledShadow = Color.Transparent,
        )
    }

    @Composable
    fun filledShadows(): NeoButtonShadows {
        val dimens = LocalNeoDimens.current
        return NeoButtonShadows(
            normalOffsetX = dimens.horizontalShadowOffset,
            normalOffsetY = dimens.verticalShadowOffset,
            disabledOffsetX = 0.dp,
            disabledOffsetY = 0.dp,
        )
    }

    @Composable
    fun textShadows(): NeoButtonShadows {
        val dimens = LocalNeoDimens.current
        return NeoButtonShadows(
            normalOffsetX = 2.dp,
            normalOffsetY = 2.dp,
            disabledOffsetX = 0.dp,
            disabledOffsetY = 0.dp,
        )
    }
}

@Composable
fun NeoButtonCore(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    colors: NeoButtonColors,
    shadows: NeoButtonShadows,
    interactionSource: MutableInteractionSource?,
    contentPadding: PaddingValues,
    content: @Composable RowScope.() -> Unit,
) {
    val dimens = LocalNeoDimens.current
    val internalSource = remember { MutableInteractionSource() }
    val resolvedSource = interactionSource ?: internalSource

    val isPressed by resolvedSource.collectIsPressedAsState()
    val isHovered by resolvedSource.collectIsHoveredAsState()

    val normalOffsetX = shadows.offsetX(true)
    val normalOffsetY = shadows.offsetY(true)
    val disabledOffsetX = shadows.offsetX(false)
    val disabledOffsetY = shadows.offsetY(false)

    val contentOffsetX by animateDpAsState(
        targetValue = when {
            !enabled -> disabledOffsetX
            isPressed -> normalOffsetX
            isHovered -> (-2).dp
            else -> 0.dp
        },
        animationSpec = spring(dampingRatio = 0.8f, stiffness = 400f),
    )

    val contentOffsetY by animateDpAsState(
        targetValue = when {
            !enabled -> disabledOffsetY
            isPressed -> normalOffsetY
            isHovered -> (-2).dp
            else -> 0.dp
        },
        animationSpec = spring(dampingRatio = 0.8f, stiffness = 400f),
    )

    val containerColor = colors.containerColor(enabled, isPressed || isHovered)
    val borderColor = colors.borderColor(enabled)
    val shadowColor = colors.shadowColor(enabled)
    val contentColor = colors.contentColor(enabled)

    Box(
        modifier = modifier.drawWithCache {
            val sx = normalOffsetX.toPx()
            val sy = normalOffsetY.toPx()
            val cachedOffset = Offset(sx, sy)
            val cachedCornerRadius = CornerRadius(dimens.cornerRadius.toPx())
            val hasShadow = (sx != 0f || sy != 0f) && shadowColor != Color.Transparent

            onDrawBehind {
                if (hasShadow) {
                    drawRoundRect(
                        color = shadowColor,
                        topLeft = cachedOffset,
                        size = size,
                        cornerRadius = cachedCornerRadius,
                    )
                }
            }
        },
        propagateMinConstraints = true,
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalNeoTextStyle provides TextStyle(color = contentColor),
        ) {
            Row(
                modifier = Modifier
                    .graphicsLayer {
                        translationX = contentOffsetX.toPx()
                        translationY = contentOffsetY.toPx()
                    }
                    .background(
                        color = containerColor,
                        shape = RoundedCornerShape(dimens.cornerRadius),
                    )
                    .then(
                        if (borderColor != Color.Transparent && dimens.borderWidth > 0.dp) {
                            Modifier.border(
                                width = dimens.borderWidth,
                                color = borderColor,
                                shape = RoundedCornerShape(dimens.cornerRadius),
                            )
                        } else Modifier
                    )
                    .clickable(
                        interactionSource = resolvedSource,
                        indication = null,
                        enabled = enabled,
                        onClick = onClick,
                    )
                    .semantics { role = Role.Button }
                    .padding(contentPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content,
            )
        }
    }
}
