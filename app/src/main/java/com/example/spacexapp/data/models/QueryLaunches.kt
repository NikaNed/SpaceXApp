package com.example.spacexapp.data.models

import com.google.gson.annotations.SerializedName

data class QueryLaunches(
    @SerializedName("links")
    val links: Links,
    @SerializedName("date_utc")
    val dateUtc: String,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("details")
    val details: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("cores")
    val cores: List<Cores>,
    @SerializedName("id")
    val id: String,
    @SerializedName("flight_number")
    val flightNumber: String,
    @SerializedName("crew")
    val crew: List<String>
)