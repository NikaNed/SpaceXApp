package com.example.spacexapp.data.models.detail

import com.google.gson.annotations.SerializedName

data class DetailQuery(
    @SerializedName("flight_number")
    val flightNumber: String
)
