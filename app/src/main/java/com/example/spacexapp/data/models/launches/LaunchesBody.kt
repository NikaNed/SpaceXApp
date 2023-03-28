package com.example.spacexapp.data.models.launches

import com.example.spacexapp.data.models.Options
import com.google.gson.annotations.SerializedName

data class LaunchesBody(
    @SerializedName("query")
    val query: DateUtc,
    @SerializedName("options")
    val options: Options
)