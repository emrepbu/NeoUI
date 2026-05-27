package com.pushforcestudio.neoui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Composable
fun NeoSwitch(
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

    val trackBackground by animateColorAsState(
        targetValue = if (checked) colors.primary else colors.background,
        animationSpec = tween(durationMillis = 150),
    )

    val thumbOffsetX by animateDpAsState(
        targetValue = if (checked) 30.dp else 8.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
    )

    Box(
        modifier = modifier
            .neoBrutalistStyle(backgroundColor = trackBackground)
            .size(width = 58.dp, height = 34.dp)
            .clickable(
                interactionSource = resolvedInteractionSource,
                indication = null,
                enabled = enabled,
                onClick = { onCheckedChange(!checked) },
            ),
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffsetX, y = 7.dp)
                .size(20.dp)
                .background(color = colors.text),
        )
    }
}
