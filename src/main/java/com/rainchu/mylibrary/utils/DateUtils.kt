package com.rainchu.mylibrary.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

object DateUtils {

   @RequiresApi(Build.VERSION_CODES.O)
   fun getMonthName(yearMonth: YearMonth) : String{

     return yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
   }

   @RequiresApi(Build.VERSION_CODES.O)
   fun daysInMonth(yearMonth: YearMonth): Int{
     return yearMonth.lengthOfMonth()
   }

   @RequiresApi(Build.VERSION_CODES.O)
   fun firstDayOfWeek(yearMonth: YearMonth): Int {
   return yearMonth.atDay(1).dayOfWeek.value % 7
   }

   @RequiresApi(Build.VERSION_CODES.O)
   fun isToday(date: LocalDate): Boolean{
    return date == LocalDate.now()
   }

   @RequiresApi(Build.VERSION_CODES.O)
   fun nextMonth(yearMonth: YearMonth) : YearMonth{
   return yearMonth.plusMonths(1)
   }

   @RequiresApi(Build.VERSION_CODES.O)
   fun previousMonth(yearMonth: YearMonth) : YearMonth{
   return yearMonth.minusMonths(1)
   }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthYear(yearMonth: YearMonth): String {
        return "${getMonthName(yearMonth)} ${yearMonth.year}"
    }

}