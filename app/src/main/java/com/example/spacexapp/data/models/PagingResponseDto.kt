package com.example.spacexapp.data.models

import com.google.gson.annotations.SerializedName

data class PagingResponseDto(
    @SerializedName("docs")
    val docs: List<QueryLaunches>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("hasPrevPage")
    val hasPrevPage: Boolean,
    @SerializedName("hasNextPage")
    val hasNextPage: Boolean,
    @SerializedName("prevPage")
    val prevPage: Int?,
    @SerializedName("nextPage")
    val nextPage: Int?
)
