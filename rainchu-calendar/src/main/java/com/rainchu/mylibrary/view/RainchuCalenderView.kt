package com.rainchu.mylibrary.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.rainchu.mylibrary.adapter.CalendarAdapter
import com.rainchu.mylibrary.controller.MonthController
import com.rainchu.mylibrary.models.CalendarDay
import com.rainchu.mylibrary.utils.DateUtils
import com.rainchu.rainchucalendar.databinding.LayoutCalendarBinding
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class RainchuCalenderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding = LayoutCalendarBinding.inflate(LayoutInflater.from(context), this, true)
    private var onOkClick: ((CalendarDay?) -> Unit)? = null
    private var onCancelClick: (() -> Unit)? = null

    private var minDate: LocalDate? = null
    private var maxDate: LocalDate? = null

    private var selectedDay: CalendarDay? = null
    private val monthController = MonthController()

    init {
        binding.rvCalendar.layoutManager = GridLayoutManager(context, 7)
        binding.btnNext.setOnClickListener {

            monthController.nextMonth()

            loadCalender()
        }

        binding.btnPrevious.setOnClickListener {

            monthController.prevMonth()

            loadCalender()
        }

        binding.tvCancel.setOnClickListener {
            onCancelClick?.invoke()
        }
        binding.tvOk.setOnClickListener {
            onOkClick?.invoke(selectedDay)
        }




        loadCalender()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadCalender() {


        binding.rvCalendar.adapter =
            CalendarAdapter(
                monthController.listOfDays().toMutableList()
            ) { day ->

                // Selected Date
                println(day.date)

                selectedDay = day

            }

        binding.tvMonth.text =

            DateUtils.getMonthYear(monthController.currentMonth())
    }
    fun setOnOkClickListener(listener: (CalendarDay?) -> Unit) {
        onOkClick = listener
    }

    fun setOnCancelClickListener(listener: () -> Unit) {
        onCancelClick = listener
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun setMinDate(date: LocalDate) {
        minDate = date
        monthController.setMinDate(date)
        loadCalender()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setMaxDate(date: LocalDate) {
        maxDate = date
        monthController.setMaxDate(date)
        loadCalender()
    }
}