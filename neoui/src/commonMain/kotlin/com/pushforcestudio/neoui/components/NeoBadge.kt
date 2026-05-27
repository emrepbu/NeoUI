package com.pushforcestudio.neoui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoTypography

@Composable
fun NeoBadge(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Unspecified,
    textColor: Color = Color.Unspecified,
) {
    val colors = LocalNeoColors.current
    val typography = LocalNeoTypography.current
    val resolvedBackground = if (backgroundColor == Color.Unspecified) colors.primary else backgroundColor
    val resolvedTextColor = if (textColor == Color.Unspecified) colors.text else textColor

    Box(
        modifier = modifier
            .neoBrutalistStyle(backgroundColor = resolvedBackground)
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        BasicText(
            text = text,
            style = TextStyle(
                color = resolvedTextColor,
                fontSize = 12.sp,
                fontWeight = typography.headingWeight,
            ),
        )
    }
}
