package com.pushforcestudio.neoui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoTypography

@Composable
fun NeoDialog(
    onDismissRequest: () -> Unit,
    title: String,
    text: String = "",
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Unspecified,
    content: (@Composable () -> Unit)? = null,
) {
    val colors = LocalNeoColors.current
    val typography = LocalNeoTypography.current
    val resolvedBackground = if (backgroundColor == Color.Unspecified) colors.background else backgroundColor

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier = modifier
                .widthIn(min = 280.dp, max = 400.dp)
                .neoBrutalistStyle(backgroundColor = resolvedBackground)
                .padding(24.dp),
        ) {
            Column {
                BasicText(
                    text = title,
                    style = TextStyle(
                color = colors.text,
                    fontSize = 20.sp,
                    fontWeight = typography.headingWeight,
                    fontFamily = typography.headingFontFamily,
                    ),
                )

                Spacer(Modifier.height(12.dp))

                if (content != null) {
                    content()
                } else if (text.isNotEmpty()) {
                    BasicText(
                        text = text,
                        style = TextStyle(
                    color = colors.text.copy(alpha = 0.8f),
                    fontSize = 14.sp,
                    fontWeight = typography.baseWeight,
                    fontFamily = typography.baseFontFamily,
                        ),
                    )
                }

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (dismissButton != null) {
                        dismissButton()
                        Spacer(Modifier.width(12.dp))
                    }
                    confirmButton()
                }
            }
        }
    }
}
