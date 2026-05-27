package com.pushforcestudio.neoui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens
import com.pushforcestudio.neoui.theme.LocalNeoTypography

@Composable
fun NeoAccordion(
    title: String,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current
    val typography = LocalNeoTypography.current
    var expanded by remember { mutableStateOf(initiallyExpanded) }

    Column(
        modifier = modifier.neoBrutalistStyle(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { expanded = !expanded },
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicText(
                text = title,
                style = TextStyle(
                    color = colors.text,
                    fontSize = 16.sp,
                    fontWeight = typography.headingWeight,
                ),
            )
            BasicText(
                text = if (expanded) "\u25BC" else "\u25BA",
                style = TextStyle(
                    color = colors.text,
                    fontSize = 14.sp,
                    fontWeight = typography.headingWeight,
                ),
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(animationSpec = tween(200)) +
                fadeIn(animationSpec = tween(200)),
            exit = shrinkVertically(animationSpec = tween(150)) +
                fadeOut(animationSpec = tween(150)),
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimens.borderWidth)
                        .background(color = colors.border),
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    content()
                }
            }
        }
    }
}
