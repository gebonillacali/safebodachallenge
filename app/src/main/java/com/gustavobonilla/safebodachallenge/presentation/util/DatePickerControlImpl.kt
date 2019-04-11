package com.gustavobonilla.safebodachallenge.presentation.util

import android.app.DatePickerDialog
import android.content.Context
import java.lang.ref.WeakReference
import java.util.*

class DatePickerControlImpl(private val context: WeakReference<Context>,
                            private val calendar: Calendar = Calendar.getInstance(),
                            private val action: (String) -> Unit): DatePickerControl {

    private val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        val fixedMonth = if (month + 1 < 10) "0${month+1}" else "${month+1}"
        val fixedDay = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
        action("$year-$fixedMonth-$fixedDay")
    }

    override fun show() {
        context.get()?.let { safeContext ->
            DatePickerDialog(safeContext,
                    datePickerListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    companion object {
        fun getCurrentCalendar(date: String): Calendar {
            return if (date.isNotBlank()) {
                val calendar = Calendar.getInstance()
                val dateComponents = date.split("-")
                calendar.set(dateComponents[0].toInt(), dateComponents[1].toInt() - 1, dateComponents[2].toInt())
                calendar
            } else Calendar.getInstance()
        }
    }
}