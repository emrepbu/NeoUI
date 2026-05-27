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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Composable
fun NeoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
    content: @Composable RowScope.() -> Unit,
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current
    val internalInteractionSource = remember { MutableInteractionSource() }
    val resolvedInteractionSource = interactionSource ?: internalInteractionSource

    val isPressed by resolvedInteractionSource.collectIsPressedAsState()
    val isHovered by resolvedInteractionSource.collectIsHoveredAsState()

    // Çökme animasyonu için mekanik bir yay (spring) efekti
    val contentOffsetX by animateDpAsState(
        targetValue = when {
            isPressed -> dimens.horizontalShadowOffset
            isHovered -> (-2).dp
            else -> 0.dp
        },
        animationSpec = spring(dampingRatio = 0.8f, stiffness = 400f)
    )

    val contentOffsetY by animateDpAsState(
        targetValue = when {
            isPressed -> dimens.verticalShadowOffset
            isHovered -> (-2).dp
            else -> 0.dp
        },
        animationSpec = spring(dampingRatio = 0.8f, stiffness = 400f)
    )

    val buttonBackground = when {
        isPressed || isHovered -> colors.primary
        else -> colors.background
    }

    // 1. DIŞ KATMAN (SABİT): Gölgeyi çizer ve yerinden hiç oynamaz.
    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = colors.shadow,
                    topLeft = Offset(
                        x = dimens.horizontalShadowOffset.toPx(),
                        y = dimens.verticalShadowOffset.toPx()
                    ),
                    size = size,
                    cornerRadius = CornerRadius(dimens.cornerRadius.toPx())
                )
            },
        // SİHİRLİ SATIR: weight(1f), fillMaxWidth() gibi dış ölçüleri içerideki Row'a zorla uygulatır
        propagateMinConstraints = true
    ) {
        // 2. İÇ KATMAN (HAREKETLİ): Çerçeve, renk ve içeriği tutar, gölgenin üstüne kayar.
        Row(
            modifier = Modifier
                .offset(x = contentOffsetX, y = contentOffsetY)
                .background(
                    color = buttonBackground,
                    shape = RoundedCornerShape(dimens.cornerRadius)
                )
                .border(
                    width = dimens.borderWidth,
                    color = colors.border,
                    shape = RoundedCornerShape(dimens.cornerRadius)
                )
                .clickable(
                    interactionSource = resolvedInteractionSource,
                    indication = null, // Material Ripple (Dalga) efektini tamamen kapatır
                    enabled = enabled,
                    onClick = onClick,
                )
                .padding(contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}