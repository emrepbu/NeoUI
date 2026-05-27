package com.pushforcestudio.neoui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Immutable
class NeoCardColors internal constructor(
    private val containerColor: Color,
    private val borderColor: Color,
    private val shadowColor: Color,
) {
    fun containerColor(): Color = containerColor

    fun borderColor(): Color = borderColor

    fun shadowColor(): Color = shadowColor
}

object NeoCardDefaults {

    @Composable
    fun filledColors(): NeoCardColors {
        val colors = LocalNeoColors.current
        return NeoCardColors(
            containerColor = colors.background,
            borderColor = colors.border,
            shadowColor = colors.shadow,
        )
    }

    @Composable
    fun outlinedColors(): NeoCardColors {
        val colors = LocalNeoColors.current
        return NeoCardColors(
            containerColor = Color.Transparent,
            borderColor = colors.border,
            shadowColor = colors.shadow,
        )
    }
}

@Composable
fun NeoCard(
    modifier: Modifier = Modifier,
    colors: NeoCardColors = NeoCardDefaults.filledColors(),
    content: @Composable (ColumnScope.() -> Unit),
) {
    val containerColor = colors.containerColor()
    val borderColor = colors.borderColor()
    val shadowColor = colors.shadowColor()

    Column(
        modifier = modifier
            .neoBrutalistStyle(
                backgroundColor = containerColor,
                borderColor = borderColor,
                shadowColor = shadowColor,
            )
            .padding(16.dp),
        content = content,
    )
}

@Composable
fun NeoOutlinedCard(
    modifier: Modifier = Modifier,
    colors: NeoCardColors = NeoCardDefaults.outlinedColors(),
    content: @Composable (ColumnScope.() -> Unit),
) {
    val containerColor = colors.containerColor()
    val borderColor = colors.borderColor()
    val shadowColor = colors.shadowColor()

    Column(
        modifier = modifier
            .neoBrutalistStyle(
                backgroundColor = containerColor,
                borderColor = borderColor,
                shadowColor = shadowColor,
            )
            .padding(16.dp),
        content = content,
    )
}
