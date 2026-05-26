package com.pushforcestudio.neoui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Composable
fun NeoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
    content: @Composable (RowScope.() -> Unit),
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current
    val internalInteractionSource = remember { MutableInteractionSource() }
    val resolvedInteractionSource = interactionSource ?: internalInteractionSource

    val isPressed by resolvedInteractionSource.collectIsPressedAsState()
    val isHovered by resolvedInteractionSource.collectIsHoveredAsState()

    val targetShadowOffset by animateDpAsState(
        targetValue = when {
            isPressed -> 0.dp
            isHovered -> 10.dp
            else -> dimens.shadowOffset
        },
    )
    val targetContentOffset by animateDpAsState(
        targetValue = when {
            isPressed -> dimens.shadowOffset
            isHovered -> (-4).dp
            else -> 0.dp
        },
    )

    val buttonBackground = when {
        isPressed || isHovered -> colors.accent
        else -> colors.background
    }

    Row(
        modifier = modifier
            .neoBrutalistStyle(
                shadowColor = colors.shadow,
                borderColor = colors.border,
                borderWidth = dimens.borderWidth,
                shadowOffsetX = targetShadowOffset,
                shadowOffsetY = targetShadowOffset,
                contentOffsetX = targetContentOffset,
                contentOffsetY = targetContentOffset,
                backgroundColor = buttonBackground,
                maxShadowOffsetX = 10.dp,
                maxShadowOffsetY = 10.dp,
            )
            .clickable(
                interactionSource = resolvedInteractionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick,
            )
            .padding(contentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content,
    )
}
