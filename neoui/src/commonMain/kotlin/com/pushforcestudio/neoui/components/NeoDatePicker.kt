package com.pushforcestudio.neoui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pushforcestudio.neoui.modifiers.neoBrutalistStyle
import com.pushforcestudio.neoui.theme.LocalNeoColors
import com.pushforcestudio.neoui.theme.LocalNeoDimens
import com.pushforcestudio.neoui.theme.LocalNeoTypography

@Immutable
class NeoDatePickerColors internal constructor(
    val containerBackground: Color,
    val borderColor: Color,
    val shadowColor: Color,
    val headerTextColor: Color,
    val weekdayTextColor: Color,
    val selectedContainer: Color,
    val selectedText: Color,
    val currentMonthText: Color,
    val adjacentMonthText: Color,
    val todayIndicator: Color,
    val selectedShadow: Color,
    val borderWidth: Dp,
    val shadowOffsetX: Dp,
    val shadowOffsetY: Dp,
    val cornerRadius: Dp,
)

object NeoDatePickerDefaults {

    @Composable
    fun colors(): NeoDatePickerColors {
        val colors = LocalNeoColors.current
        val dimens = LocalNeoDimens.current
        return NeoDatePickerColors(
            containerBackground = colors.surface,
            borderColor = colors.border,
            shadowColor = colors.shadow,
            headerTextColor = colors.text,
            weekdayTextColor = colors.text.copy(alpha = 0.5f),
            selectedContainer = colors.primary,
            selectedText = colors.text,
            currentMonthText = colors.text,
            adjacentMonthText = colors.text.copy(alpha = 0.3f),
            todayIndicator = colors.secondary,
            selectedShadow = colors.shadow,
            borderWidth = dimens.borderWidth,
            shadowOffsetX = dimens.horizontalShadowOffset,
            shadowOffsetY = dimens.verticalShadowOffset,
            cornerRadius = dimens.cornerRadius,
        )
    }
}

@Composable
fun NeoDatePicker(
    selectedDate: NeoDate? = null,
    onDateSelected: (NeoDate) -> Unit,
    modifier: Modifier = Modifier,
    today: NeoDate? = null,
    colors: NeoDatePickerColors = NeoDatePickerDefaults.colors(),
) {
    val typography = LocalNeoTypography.current

    val initialMonth = selectedDate?.month ?: today?.month ?: 1
    val initialYear = selectedDate?.year ?: today?.year ?: 2024

    var currentMonth by remember { mutableStateOf(initialMonth) }
    var currentYear by remember { mutableStateOf(initialYear) }

    val label = "${monthLabel(currentMonth)} $currentYear"
    val grid = remember(currentMonth, currentYear) { generateGrid(currentMonth, currentYear) }

    Box(
        modifier = modifier
            .neoBrutalistStyle(
                backgroundColor = colors.containerBackground,
                borderColor = colors.borderColor,
                shadowColor = colors.shadowColor,
            )
            .padding(12.dp),
    ) {
        Column {
            MonthHeader(
                label = label,
                colors = colors,
                typography = typography,
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

            WeekdaysRow(colors = colors, typography = typography)

            Spacer(Modifier.height(4.dp))

            DaysGrid(
                grid = grid,
                currentMonth = currentMonth,
                currentYear = currentYear,
                selectedDate = selectedDate,
                today = today,
                colors = colors,
                onDateSelected = onDateSelected,
            )
        }
    }
}

@Composable
private fun MonthHeader(
    label: String,
    colors: NeoDatePickerColors,
    typography: com.pushforcestudio.neoui.theme.NeoTypography,
    onPrev: () -> Unit,
    onNext: () -> Unit,
) {
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
                    color = colors.headerTextColor,
                    fontSize = 22.sp,
                    fontWeight = typography.headingWeight,
                ),
            )
        }

        BasicText(
            text = label,
            modifier = Modifier.weight(1f),
            style = TextStyle(
                color = colors.headerTextColor,
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
                    color = colors.headerTextColor,
                    fontSize = 22.sp,
                    fontWeight = typography.headingWeight,
                ),
            )
        }
    }
}

@Composable
private fun WeekdaysRow(
    colors: NeoDatePickerColors,
    typography: com.pushforcestudio.neoui.theme.NeoTypography,
) {
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    Row(modifier = Modifier.fillMaxWidth()) {
        days.forEach { day ->
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                BasicText(
                    text = day,
                    style = TextStyle(
                        color = colors.weekdayTextColor,
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
    colors: NeoDatePickerColors,
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
                                colors = colors,
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
    colors: NeoDatePickerColors,
    onClick: () -> Unit,
) {
    val typography = LocalNeoTypography.current

    val textColor = when {
        isSelected -> colors.selectedText
        isCurrentMonth -> colors.currentMonthText
        else -> colors.adjacentMonthText
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
                .drawWithCache {
                    val cachedOffset = Offset(
                        x = colors.shadowOffsetX.toPx(),
                        y = colors.shadowOffsetY.toPx(),
                    )
                    val cachedCornerRadius = CornerRadius(colors.cornerRadius.toPx())

                    onDrawBehind {
                        drawRoundRect(
                            color = colors.selectedShadow,
                            topLeft = cachedOffset,
                            size = size,
                            cornerRadius = cachedCornerRadius,
                        )
                    }
                },
            propagateMinConstraints = true,
        ) {
            Box(
                modifier = Modifier
                    .offset(x = colors.shadowOffsetX, y = colors.shadowOffsetY)
                    .fillMaxWidth()
                    .size(cellSize)
                    .background(color = colors.selectedContainer, shape = RoundedCornerShape(colors.cornerRadius))
                    .border(width = colors.borderWidth, color = colors.borderColor, shape = RoundedCornerShape(colors.cornerRadius))
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
                .drawWithCache {
                    val lineWidth = 3.dp.toPx()
                    onDrawBehind {
                        if (isToday) {
                            val lineY = size.height - lineWidth / 2f
                            drawLine(
                                color = colors.todayIndicator,
                                start = Offset(size.width * 0.25f, lineY),
                                end = Offset(size.width * 0.75f, lineY),
                                strokeWidth = lineWidth,
                            )
                        }
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
