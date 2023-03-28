package com.example.spacexapp.data.models

import com.google.gson.annotations.SerializedName

data class Options(
    @SerializedName("sort")
    val sort: String,
    @SerializedName("page")
    val page: Int
)