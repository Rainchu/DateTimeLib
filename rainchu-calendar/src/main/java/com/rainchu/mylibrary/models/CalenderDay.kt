package com.rainchu.mylibrary.models

import java.time.LocalDate
data class CalendarDay(
    val date: LocalDate?,
    val day: Int = 0,
    val isCurrentMonth: Boolean = false,
    var isSelected: Boolean = false,
    val isToday: Boolean = false,
    val isDisabled: Boolean = false
)