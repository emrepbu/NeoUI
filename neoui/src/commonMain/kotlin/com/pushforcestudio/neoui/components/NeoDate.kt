package com.pushforcestudio.neoui.components

data class NeoDate(val year: Int, val month: Int, val day: Int)

internal fun isLeapYear(year: Int): Boolean =
    (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

internal fun daysInMonth(month: Int, year: Int): Int = when (month) {
    1, 3, 5, 7, 8, 10, 12 -> 31
    4, 6, 9, 11 -> 30
    2 -> if (isLeapYear(year)) 29 else 28
    else -> 30
}

internal fun dayOfWeek(year: Int, month: Int, day: Int): Int {
    val t = intArrayOf(0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4)
    var y = year
    if (month < 3) y -= 1
    return (y + y / 4 - y / 100 + y / 400 + t[month - 1] + day) % 7
}

internal fun nextMonth(month: Int, year: Int): Pair<Int, Int> =
    if (month == 12) 1 to (year + 1) else (month + 1) to year

internal fun prevMonth(month: Int, year: Int): Pair<Int, Int> =
    if (month == 1) 12 to (year - 1) else (month - 1) to year

fun monthLabel(month: Int): String = when (month) {
    1 -> "January"; 2 -> "February"; 3 -> "March"; 4 -> "April"
    5 -> "May"; 6 -> "June"; 7 -> "July"; 8 -> "August"
    9 -> "September"; 10 -> "October"; 11 -> "November"; 12 -> "December"
    else -> "Unknown"
}

internal fun generateGrid(month: Int, year: Int): List<NeoDate?> {
    val days = daysInMonth(month, year)
    val firstDow = dayOfWeek(year, month, 1)
    val startOffset = (firstDow + 6) % 7

    val (prevM, prevY) = prevMonth(month, year)
    val prevDays = daysInMonth(prevM, prevY)

    val grid = mutableListOf<NeoDate?>()

    for (i in (prevDays - startOffset + 1)..prevDays) {
        grid.add(NeoDate(prevY, prevM, i))
    }

    for (day in 1..days) {
        grid.add(NeoDate(year, month, day))
    }

    val (nextM, nextY) = nextMonth(month, year)
    var nextDay = 1
    while (grid.size < 42) {
        grid.add(NeoDate(nextY, nextM, nextDay))
        nextDay++
    }

    return grid
}
