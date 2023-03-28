package com.example.spacexapp.data.models.crew

import com.google.gson.annotations.SerializedName

data class CrewBody(
    @SerializedName("query")
    val query: CrewQuery,
    )