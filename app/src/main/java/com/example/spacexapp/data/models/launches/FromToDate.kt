package com.example.spacexapp.data.models.launches

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*

data class FromToDate(
    @SerializedName("\$gte")
    val gte: String = fromDate(),
    @SerializedName("\$lte")
    val lte: String = toDate()
) {
    companion object {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US)

        private fun fromDate(): String = LocalDate.of(2021, 1, 1)
            .atStartOfDay()
            .toInstant(ZoneOffset.UTC).toString()

        private fun toDate() = dateFormat.format(Date())
    }
}
