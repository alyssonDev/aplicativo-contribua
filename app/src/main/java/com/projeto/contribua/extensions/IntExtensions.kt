package com.projeto.contribua.extensions

fun Int.formatDayOfDate(): String {
    var valueFormat = "$this"
    if (this < 10) {
        valueFormat = "0$this"
    }
    return valueFormat
}

fun Int.formateMonthOfDate(): String{
    var valueFormat = "$this"
    if (this < 10) {
        valueFormat = "0$this"
    }
    return valueFormat
}