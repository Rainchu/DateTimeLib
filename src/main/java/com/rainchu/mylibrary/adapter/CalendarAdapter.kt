package com.rainchu.mylibrary.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.rainchu.mylibrary.models.CalendarDay
import com.rainchu.rainchucalendar.databinding.ItemDayBinding

class CalendarAdapter(
    private val listOfDay: MutableList<CalendarDay>,
    private val onClickDay: (CalendarDay) -> Unit = {}
) : RecyclerView.Adapter<CalendarAdapter.CalendarHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarHolder {

        val binding = ItemDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CalendarHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CalendarHolder, position: Int) {

        val day = listOfDay[position]

        if (day.date == null) {

            holder.binding.tvDay.text = ""

            holder.binding.cardDay.visibility = View.INVISIBLE

            return
        }

        holder.binding.cardDay.visibility = View.VISIBLE


        holder.binding.tvDay.text = day.date.dayOfMonth.toString()

        // Reset state (VERY IMPORTANT because RecyclerView reuses views)
        holder.binding.cardDay.setCardBackgroundColor(Color.TRANSPARENT)
        holder.binding.tvDay.setTextColor(Color.BLACK)

        if (day.isDisabled) {

            holder.binding.tvDay.alpha = 0.3f
            holder.binding.root.isEnabled = false

        } else {

            holder.binding.tvDay.alpha = 1f
            holder.binding.root.isEnabled = true

        }

        // Today
        if (day.isToday) {
            holder.binding.cardDay.setCardBackgroundColor(Color.parseColor("#2196F3"))
            holder.binding.tvDay.setTextColor(Color.WHITE)
        }

        // Selected (Overrides Today)
        if (day.isSelected) {
            holder.binding.cardDay.setCardBackgroundColor(Color.parseColor("#4CAF50"))
            holder.binding.tvDay.setTextColor(Color.WHITE)
        }

        holder.binding.root.setOnClickListener {

            if (day.isDisabled) return@setOnClickListener


            // Remove previous selection
            if (selectedPosition != RecyclerView.NO_POSITION) {

                listOfDay[selectedPosition].isSelected = false
                notifyItemChanged(selectedPosition)

            }

            // New selection
            selectedPosition = holder.bindingAdapterPosition

            if (selectedPosition != RecyclerView.NO_POSITION) {

                listOfDay[selectedPosition].isSelected = true
                notifyItemChanged(selectedPosition)

                onClickDay.invoke(listOfDay[selectedPosition])

            }
        }
    }

    override fun getItemCount(): Int = listOfDay.size

    inner class CalendarHolder(
        val binding: ItemDayBinding
    ) : RecyclerView.ViewHolder(binding.root)
}