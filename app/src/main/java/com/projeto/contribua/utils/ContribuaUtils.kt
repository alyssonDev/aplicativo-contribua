package com.projeto.contribua.utils

import com.projeto.contribua.extensions.formatDayOfDate
import com.projeto.contribua.extensions.formateMonthOfDate


fun formatdateInString(day: Int, month: Int, year: Int): String {
    val dayFormat = day.formatDayOfDate()
    val monthFormat = month.formateMonthOfDate()

    return String.format("%s/%s/%s", dayFormat, monthFormat, year)
}