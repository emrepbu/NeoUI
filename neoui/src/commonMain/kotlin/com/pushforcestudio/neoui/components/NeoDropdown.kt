package com.pushforcestudio.neoui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens
import kotlin.math.roundToInt

@Composable
fun NeoDropdown(
    items: List<String>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Select...",
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current
    val density = LocalDensity.current

    var expanded by remember { mutableStateOf(false) }
    var anchorPosition by remember { mutableStateOf(Offset.Zero) }
    var anchorSize by remember { mutableStateOf(IntSize.Zero) }

    val selectedLabel = items.getOrElse(selectedIndex) { placeholder }

    val positionProvider = remember(anchorPosition, anchorSize) {
        object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize,
            ): IntOffset {
                var x = anchorPosition.x.roundToInt()
                var y = (anchorPosition.y + anchorSize.height).roundToInt()
                if (x + popupContentSize.width > windowSize.width) {
                    x = (windowSize.width - popupContentSize.width).coerceAtLeast(0)
                }
                if (y + popupContentSize.height > windowSize.height) {
                    y = (anchorPosition.y - popupContentSize.height).roundToInt().coerceAtLeast(0)
                }
                return IntOffset(x, y)
            }
        }
    }

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .neoBrutalistStyle()
                .onGloballyPositioned { coordinates ->
                    anchorPosition = coordinates.positionInWindow()
                    anchorSize = coordinates.size
                }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { expanded = !expanded },
                )
                .padding(horizontal = 16.dp, vertical = 14.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicText(
                    text = if (items.isEmpty()) placeholder else selectedLabel,
                    modifier = Modifier.weight(1f),
                    style = TextStyle(
                        color = if (items.isEmpty()) colors.text.copy(alpha = 0.5f) else colors.text,
                        fontSize = 16.sp,
                    ),
                )
                BasicText(
                    text = "\u25BC",
                    style = TextStyle(
                        color = colors.text,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                    ),
                )
            }
        }

        if (expanded && items.isNotEmpty()) {
            Popup(
                popupPositionProvider = positionProvider,
                onDismissRequest = { expanded = false },
            ) {
                Box(
                    modifier = Modifier
                        .widthIn(max = 400.dp)
                        .neoBrutalistStyle(),
                ) {
                    Column(
                        modifier = Modifier
                            .heightIn(max = 250.dp)
                            .verticalScroll(rememberScrollState()),
                    ) {
                        items.forEachIndexed { index, item ->
                            val isSelected = index == selectedIndex
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                            onItemSelected(index)
                                            expanded = false
                                        },
                                    )
                                    .let { mod ->
                                        if (isSelected) mod.let {
                                            it.then(
                                                Modifier.drawBehind {
                                                    drawRect(color = colors.primary)
                                                },
                                            )
                                        } else mod
                                    }
                                    .padding(horizontal = 12.dp, vertical = 10.dp)
                                    .drawBehind {
                                        drawLine(
                                            color = colors.border,
                                            start = Offset(0f, size.height),
                                            end = Offset(size.width, size.height),
                                            strokeWidth = dimens.borderWidth.toPx(),
                                        )
                                    },
                            ) {
                                BasicText(
                                    text = item,
                                    style = TextStyle(
                                        color = colors.text,
                                        fontSize = 14.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
