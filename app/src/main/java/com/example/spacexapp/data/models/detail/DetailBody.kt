package com.example.spacexapp.data.models.detail

import com.google.gson.annotations.SerializedName

data class DetailBody(
    @SerializedName("query")
    val query: DetailQuery,
)