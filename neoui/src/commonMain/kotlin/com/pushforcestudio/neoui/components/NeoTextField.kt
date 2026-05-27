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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoTypography

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
    val typography = LocalNeoTypography.current
    val internalInteractionSource = remember { MutableInteractionSource() }
    val resolvedInteractionSource = interactionSource ?: internalInteractionSource

    val isFocused by resolvedInteractionSource.collectIsFocusedAsState()

    val shadowColor = when {
        isError -> colors.error
        isFocused -> colors.primary
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
                backgroundColor = colors.background,
            )
            .semantics {
                if (hint.isNotEmpty()) {
                    contentDescription = hint
                }
            }
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
                color = colors.text,
                fontSize = 16.sp,
                fontFamily = typography.baseFontFamily,
            ),
            cursorBrush = SolidColor(colors.primary),
            decorationBox = { innerTextField ->
                Box {
                    if (value.isEmpty() && hint.isNotEmpty()) {
                        BasicText(
                            text = hint,
                            style = TextStyle(
                                color = colors.text.copy(alpha = 0.5f),
                                fontSize = 16.sp,
                                fontFamily = typography.baseFontFamily,
                            ),
                        )
                    }
                    innerTextField()
                }
            },
        )
    }
}
