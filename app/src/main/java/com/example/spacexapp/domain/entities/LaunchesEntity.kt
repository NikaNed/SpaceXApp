package com.example.spacexapp.domain.entities

data class LaunchesEntity(
    val small: String? = null,
    val name: String,
    val flight: Int,
    val success: Boolean? = null,
    val date_utc: String,
    val id: String,
    val flightNumber: String,
)