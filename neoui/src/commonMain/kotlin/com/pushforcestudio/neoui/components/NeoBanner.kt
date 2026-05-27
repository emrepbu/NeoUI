package com.pushforcestudio.neoui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoTypography

enum class BannerType {
    INFO, SUCCESS, ERROR
}

@Composable
fun NeoBanner(
    message: String,
    modifier: Modifier = Modifier,
    type: BannerType = BannerType.INFO,
    onDismiss: (() -> Unit)? = null,
) {
    val colors = LocalNeoColors.current
    val typography = LocalNeoTypography.current

    val backgroundColor = when (type) {
        BannerType.INFO -> Color(0xFF00E5FF)
        BannerType.SUCCESS -> Color(0xFF39FF14)
        BannerType.ERROR -> Color(0xFFFF0000)
    }

    Row(
        modifier = modifier
            .neoBrutalistStyle(backgroundColor = backgroundColor)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicText(
            text = message,
            modifier = Modifier.weight(1f),
            style = TextStyle(
                color = colors.text,
                fontSize = 14.sp,
                fontWeight = typography.baseWeight,
                fontFamily = typography.baseFontFamily,
            ),
        )

        if (onDismiss != null) {
            Spacer(Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onDismiss,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                BasicText(
                    text = "\u2715",
                    style = TextStyle(
                        color = colors.text,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                    ),
                )
            }
        }
    }
}
