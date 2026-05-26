package com.pushforcestudio.neoui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens

@Composable
fun NeoCard(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current

    Column(
        modifier = modifier
            .neoBrutalistStyle(
                shadowColor = colors.shadow,
                borderColor = colors.border,
                borderWidth = dimens.borderWidth,
                shadowOffsetX = dimens.shadowOffset,
                shadowOffsetY = dimens.shadowOffset,
                backgroundColor = colors.background,
                maxShadowOffsetX = dimens.shadowOffset,
                maxShadowOffsetY = dimens.shadowOffset,
            )
            .padding(16.dp),
        content = content,
    )
}
