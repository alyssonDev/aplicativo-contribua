package com.projeto.contribua.utils

import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.projeto.contribua.R
import com.projeto.contribua.extensions.formatDayOfDate
import com.projeto.contribua.extensions.formateMonthOfDate


fun formatdateInString(day: Int, month: Int, year: Int): String {
    val dayFormat = day.formatDayOfDate()
    val monthFormat = month.formateMonthOfDate()

    return String.format("%s/%s/%s", dayFormat, monthFormat, year)
}

fun MutableList<SlideModel>.populateList() {
    this.add(
        SlideModel(
            R.drawable.bandeira_missoes,
            "Bandeira de MISSÕES. Missões está no CORAÇÃO DE DEUS",
            ScaleTypes.FIT
        )
    )
    this.add(
        SlideModel(
            R.drawable.img_gerson,
            "Pr. Gerson Gila em missões",
            ScaleTypes.FIT
        )
    )
    this.add(
        SlideModel(
            R.drawable.img_evangelizacao,
            "Ev. Augusto Félix em missões",
            ScaleTypes.FIT
        )
    )
    this.add(
        SlideModel(
            R.drawable.img_kenedy,
            "Missões em KENNEDY STREET",
            ScaleTypes.FIT
        )
    )
}