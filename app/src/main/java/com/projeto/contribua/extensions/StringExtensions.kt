package com.projeto.contribua.extensions

import com.projeto.contribua.R

fun String.getCongregationByArea(): Int {
    return when (this) {
        "Área 1" -> R.array.list_area1
        "Área 2" -> R.array.list_area2
        "Área 3" -> R.array.list_area3
        "Área 4" -> R.array.list_area4
        "Área 5" -> R.array.list_area5
        "Área 6" -> R.array.list_area6
        "Área 7" -> R.array.list_area7
        "Área 8" -> R.array.list_area8
        "Área 9" -> R.array.list_area9
        "Área 10" -> R.array.list_area10
        "Área 11" -> R.array.list_area11
        "Área 12" -> R.array.list_area12
        else -> R.array.list_empty
    }
}

fun String.getDistrictAttorneyAtCongregationName(): Int {
    return when (this) {
        "Tiúma 1" -> R.array.district_list_tiuma1
        "Tiúma 2" -> R.array.district_list_tiuma1
        "Tiúma 3" -> R.array.district_list_tiuma1
        else -> R.array.list_empty
    }
}
