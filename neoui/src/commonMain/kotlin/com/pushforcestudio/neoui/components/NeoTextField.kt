package com.pushforcestudio.neoui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Composable
fun NeoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    isError: Boolean = false,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current
    val internalInteractionSource = remember { MutableInteractionSource() }
    val resolvedInteractionSource = interactionSource ?: internalInteractionSource

    val isFocused by resolvedInteractionSource.collectIsFocusedAsState()

    val shadowColor = when {
        isError -> colors.error
        isFocused -> colors.accent
        else -> colors.shadow
    }

    val borderColor = when {
        isError -> colors.error
        else -> colors.border
    }

    Box(
        modifier = modifier
            .neoBrutalistStyle(
                shadowColor = shadowColor,
                borderColor = borderColor,
                borderWidth = dimens.borderWidth,
                shadowOffsetX = dimens.shadowOffset,
                shadowOffsetY = dimens.shadowOffset,
                backgroundColor = colors.background,
                maxShadowOffsetX = dimens.shadowOffset,
                maxShadowOffsetY = dimens.shadowOffset,
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = singleLine,
            interactionSource = resolvedInteractionSource,
            textStyle = TextStyle(
                color = colors.foreground,
                fontSize = 16.sp,
            ),
            cursorBrush = SolidColor(colors.accent),
            decorationBox = { innerTextField ->
                Box {
                    if (value.isEmpty() && hint.isNotEmpty()) {
                        BasicText(
                            text = hint,
                            style = TextStyle(
                                color = colors.foreground.copy(alpha = 0.5f),
                                fontSize = 16.sp,
                            ),
                        )
                    }
                    innerTextField()
                }
            },
        )
    }
}
