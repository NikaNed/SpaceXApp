package com.example.spacexapp.data.models

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("patch")
    val patch: Patch
)
