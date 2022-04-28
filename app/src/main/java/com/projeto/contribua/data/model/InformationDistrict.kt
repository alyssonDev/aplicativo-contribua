package com.projeto.contribua.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InformationDistrict(
    val congregationArea: String,
    val congregationName: String,
    val districtName: String
) : Parcelable
