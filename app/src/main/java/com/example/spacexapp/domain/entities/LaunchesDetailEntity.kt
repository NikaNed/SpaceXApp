package com.example.spacexapp.domain.entities

data class LaunchesDetailEntity(
    val large: String? = null,
    val name: String,
    val flight: Int,
    val success: Boolean? = null,
    val date_utc: String,
    val id: String,
    val details: String? = null,
    val crew: List<String>,
)
