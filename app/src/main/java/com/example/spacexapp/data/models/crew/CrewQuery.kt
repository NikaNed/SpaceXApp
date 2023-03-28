package com.example.spacexapp.data.models.crew

import com.google.gson.annotations.SerializedName

data class CrewQuery(
    @SerializedName("launches")
    val launches: String
)
