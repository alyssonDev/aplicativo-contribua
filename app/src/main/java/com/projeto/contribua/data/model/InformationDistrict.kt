package com.projeto.contribua.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InformationDistrict(
    var congregationArea: String = "",
    var congregationName: String = "",
    var districtName: String = ""
) : Parcelable
