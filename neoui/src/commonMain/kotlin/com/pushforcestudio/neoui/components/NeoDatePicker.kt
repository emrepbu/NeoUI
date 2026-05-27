package com.pushforcestudio.neoui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens
import com.pushforcestudio.neoui.theme.LocalNeoTypography

@Composable
fun NeoDatePicker(
    selectedDate: NeoDate? = null,
    onDateSelected: (NeoDate) -> Unit,
    modifier: Modifier = Modifier,
    today: NeoDate? = null,
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current
    val typography = LocalNeoTypography.current

    val initialMonth = selectedDate?.month ?: today?.month ?: 1
    val initialYear = selectedDate?.year ?: today?.year ?: 2024

    var currentMonth by remember { mutableStateOf(initialMonth) }
    var currentYear by remember { mutableStateOf(initialYear) }

    val label = "${monthLabel(currentMonth)} $currentYear"
    val grid = remember(currentMonth, currentYear) { generateGrid(currentMonth, currentYear) }

    Box(
        modifier = modifier
            .neoBrutalistStyle(backgroundColor = colors.surface)
            .padding(12.dp),
    ) {
        Column {
            MonthHeader(
                label = label,
                onPrev = {
                    val (m, y) = prevMonth(currentMonth, currentYear)
                    currentMonth = m; currentYear = y
                },
                onNext = {
                    val (m, y) = nextMonth(currentMonth, currentYear)
                    currentMonth = m; currentYear = y
                },
            )

            Spacer(Modifier.height(8.dp))

            WeekdaysRow()

            Spacer(Modifier.height(4.dp))

            DaysGrid(
                grid = grid,
                currentMonth = currentMonth,
                currentYear = currentYear,
                selectedDate = selectedDate,
                today = today,
                onDateSelected = onDateSelected,
            )
        }
    }
}

@Composable
private fun MonthHeader(
    label: String,
    onPrev: () -> Unit,
    onNext: () -> Unit,
) {
    val colors = LocalNeoColors.current
    val typography = LocalNeoTypography.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onPrev,
                ),
            contentAlignment = Alignment.Center,
        ) {
            BasicText(
                text = "\u2039",
                style = TextStyle(
                    color = colors.text,
                    fontSize = 22.sp,
                    fontWeight = typography.headingWeight,
                ),
            )
        }

        BasicText(
            text = label,
            modifier = Modifier.weight(1f),
            style = TextStyle(
                color = colors.text,
                fontSize = 16.sp,
                fontWeight = typography.headingWeight,
                fontFamily = typography.headingFontFamily,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            ),
        )

        Box(
            modifier = Modifier
                .size(36.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onNext,
                ),
            contentAlignment = Alignment.Center,
        ) {
            BasicText(
                text = "\u203A",
                style = TextStyle(
                    color = colors.text,
                    fontSize = 22.sp,
                    fontWeight = typography.headingWeight,
                ),
            )
        }
    }
}

@Composable
private fun WeekdaysRow() {
    val colors = LocalNeoColors.current
    val typography = LocalNeoTypography.current
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    Row(modifier = Modifier.fillMaxWidth()) {
        days.forEach { day ->
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                BasicText(
                    text = day,
                    style = TextStyle(
                        color = colors.text.copy(alpha = 0.5f),
                        fontSize = 11.sp,
                        fontWeight = typography.baseWeight,
                        fontFamily = typography.baseFontFamily,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    ),
                )
            }
        }
    }
}

@Composable
private fun DaysGrid(
    grid: List<NeoDate?>,
    currentMonth: Int,
    currentYear: Int,
    selectedDate: NeoDate?,
    today: NeoDate?,
    onDateSelected: (NeoDate) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        for (row in 0..5) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (col in 0..6) {
                    val index = row * 7 + col
                    val date = grid.getOrNull(index)
                    val isCurrentMonth = date != null && date.month == currentMonth && date.year == currentYear
                    val isSelected = date != null && selectedDate != null &&
                        date.year == selectedDate.year &&
                        date.month == selectedDate.month &&
                        date.day == selectedDate.day
                    val isToday = date != null && today != null &&
                        date.year == today.year &&
                        date.month == today.month &&
                        date.day == today.day

                    Box(modifier = Modifier.weight(1f)) {
                        if (date != null) {
                            NeoDayCell(
                                day = date.day,
                                isCurrentMonth = isCurrentMonth,
                                isSelected = isSelected,
                                isToday = isToday,
                                onClick = { onDateSelected(date) },
                            )
                        } else {
                            Spacer(Modifier.size(36.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NeoDayCell(
    day: Int,
    isCurrentMonth: Boolean,
    isSelected: Boolean,
    isToday: Boolean,
    onClick: () -> Unit,
) {
    val colors = LocalNeoColors.current
    val dimens = LocalNeoDimens.current
    val typography = LocalNeoTypography.current

    val textColor = when {
        isSelected -> colors.text
        isCurrentMonth -> colors.text
        else -> colors.text.copy(alpha = 0.3f)
    }

    val fontWeight = when {
        isSelected || isToday -> typography.headingWeight
        else -> typography.baseWeight
    }

    val cellSize = 36.dp

    if (isSelected) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRoundRect(
                        color = colors.shadow,
                        topLeft = Offset(
                            x = dimens.horizontalShadowOffset.toPx(),
                            y = dimens.verticalShadowOffset.toPx(),
                        ),
                        size = size,
                        cornerRadius = CornerRadius(dimens.cornerRadius.toPx()),
                    )
                },
            propagateMinConstraints = true,
        ) {
            Box(
                modifier = Modifier
                    .offset(x = dimens.horizontalShadowOffset, y = dimens.verticalShadowOffset)
                    .fillMaxWidth()
                    .size(cellSize)
                    .background(
                        color = colors.primary,
                        shape = RoundedCornerShape(dimens.cornerRadius),
                    )
                    .border(
                        width = dimens.borderWidth,
                        color = colors.border,
                        shape = RoundedCornerShape(dimens.cornerRadius),
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                BasicText(
                    text = day.toString(),
                    style = TextStyle(
                        color = textColor,
                        fontSize = 14.sp,
                        fontWeight = fontWeight,
                        fontFamily = typography.baseFontFamily,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    ),
                )
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(cellSize)
                .drawBehind {
                    if (isToday) {
                        val lineWidth = 3.dp.toPx()
                        val lineY = size.height - lineWidth / 2f
                        drawLine(
                            color = colors.secondary,
                            start = Offset(size.width * 0.25f, lineY),
                            end = Offset(size.width * 0.75f, lineY),
                            strokeWidth = lineWidth,
                        )
                    }
                }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick,
                ),
            contentAlignment = Alignment.Center,
        ) {
            BasicText(
                text = day.toString(),
                style = TextStyle(
                    color = textColor,
                    fontSize = 14.sp,
                    fontWeight = fontWeight,
                    fontFamily = typography.baseFontFamily,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                ),
            )
        }
    }
}
