package com.example.spacexapp.data.models.launches

import com.google.gson.annotations.SerializedName

data class DateUtc(
    @SerializedName("date_utc")
    val dateUtc: FromToDate
)