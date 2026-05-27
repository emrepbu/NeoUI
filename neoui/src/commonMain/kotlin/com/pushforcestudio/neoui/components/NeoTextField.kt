package com.pushforcestudio.neoui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens
import com.pushforcestudio.neoui.theme.LocalNeoTypography

object NeoTextFieldDefaults {

    @Composable
    fun colors(): NeoTextFieldColors {
        val colors = LocalNeoColors.current
        val dimens = LocalNeoDimens.current
        return NeoTextFieldColors(
            normalShadow = colors.shadow,
            focusedShadow = colors.primary,
            errorShadow = colors.error,
            normalBorder = colors.border,
            errorBorder = colors.error,
            disabledBorder = colors.border.copy(alpha = 0.3f),
            containerColor = colors.background,
            disabledContainerColor = Color(0xFFCCCCCC),
            textColor = colors.text,
            disabledTextColor = colors.text.copy(alpha = 0.4f),
            hintColor = colors.text.copy(alpha = 0.5f),
            cursorColor = colors.primary,
            borderWidth = dimens.borderWidth,
            shadowOffsetX = dimens.horizontalShadowOffset,
            shadowOffsetY = dimens.verticalShadowOffset,
        )
    }
}

@Immutable
class NeoTextFieldColors internal constructor(
    val normalShadow: Color,
    val focusedShadow: Color,
    val errorShadow: Color,
    val normalBorder: Color,
    val errorBorder: Color,
    val disabledBorder: Color,
    val containerColor: Color,
    val disabledContainerColor: Color,
    val textColor: Color,
    val disabledTextColor: Color,
    val hintColor: Color,
    val cursorColor: Color,
    val borderWidth: Dp,
    val shadowOffsetX: Dp,
    val shadowOffsetY: Dp,
)

@Composable
fun NeoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    isError: Boolean = false,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    colors: NeoTextFieldColors = NeoTextFieldDefaults.colors(),
    interactionSource: MutableInteractionSource? = null,
) {
    val typography = LocalNeoTypography.current
    val internalInteractionSource = remember { MutableInteractionSource() }
    val resolvedInteractionSource = interactionSource ?: internalInteractionSource

    val isFocused by resolvedInteractionSource.collectIsFocusedAsState()

    val effectiveShadowColor = when {
        !enabled -> Color.Transparent
        isError -> colors.errorShadow
        isFocused -> colors.focusedShadow
        else -> colors.normalShadow
    }

    val effectiveBorderColor = when {
        !enabled -> colors.disabledBorder
        isError -> colors.errorBorder
        else -> colors.normalBorder
    }

    val effectiveBackground = if (!enabled) colors.disabledContainerColor else colors.containerColor
    val effectiveTextColor = if (enabled) colors.textColor else colors.disabledTextColor

    val effectiveShadowOffsetX = if (enabled) colors.shadowOffsetX else 0.dp
    val effectiveShadowOffsetY = if (enabled) colors.shadowOffsetY else 0.dp

    Box(
        modifier = modifier
            .neoBrutalistStyle(
                shadowColor = effectiveShadowColor,
                borderColor = effectiveBorderColor,
                backgroundColor = effectiveBackground,
                shadowOffsetX = effectiveShadowOffsetX,
                shadowOffsetY = effectiveShadowOffsetY,
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
                color = effectiveTextColor,
                fontSize = 16.sp,
                fontFamily = typography.baseFontFamily,
            ),
            cursorBrush = SolidColor(colors.cursorColor),
            decorationBox = { innerTextField ->
                Box {
                    if (value.isEmpty() && hint.isNotEmpty()) {
                        BasicText(
                            text = hint,
                            style = TextStyle(
                                color = if (enabled) colors.hintColor else colors.hintColor.copy(alpha = 0.3f),
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
