package com.rainchu.mylibrary.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.rainchu.mylibrary.models.CalendarDay
import java.time.LocalDate
import java.time.YearMonth

class MonthController {

    private var minDate: LocalDate? = null
    private var maxDate: LocalDate? = null
    @RequiresApi(Build.VERSION_CODES.O)
    private var yearMonth = YearMonth.now()


    @RequiresApi(Build.VERSION_CODES.O)
    fun currentMonth() : YearMonth{
        return yearMonth
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonth(){
        yearMonth = yearMonth.plusMonths(1)
    }

    fun setMinDate(){

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun prevMonth(){
        yearMonth = yearMonth.minusMonths(1)
    }

    fun setMinDate(date: LocalDate) {
        minDate = date
    }

    fun setMaxDate(date: LocalDate) {
        maxDate = date
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun listOfDays(): MutableList<CalendarDay> {

        val daysList = mutableListOf<CalendarDay>()

        val today = LocalDate.now()

        val totalDays = yearMonth.lengthOfMonth()

        // Sunday = 0
        val firstDay = yearMonth.atDay(1).dayOfWeek.value % 7




        // Empty cells before first day
        repeat(firstDay) {

            daysList.add(
                CalendarDay(
                    date = null,
                    day = 0
                )
            )

        }

        // Current month
        for (day in 1..totalDays) {
            val date = yearMonth.atDay(day)

            val isDisabled =
                (minDate != null && date.isBefore(minDate!!)) ||
                        (maxDate != null && date.isAfter(maxDate!!))
           // val date = yearMonth.atDay(day)

            daysList.add(
                CalendarDay(
                    date = date,
                    day = day,
                    isCurrentMonth = true,
                    isToday = date == today,
                    isDisabled = isDisabled
                )
            )
        }

        // Fill remaining cells to 42
        while (daysList.size < 42) {

            daysList.add(
                CalendarDay(
                    date = null,
                    day = 0
                )
            )
        }

        return daysList
    }
}