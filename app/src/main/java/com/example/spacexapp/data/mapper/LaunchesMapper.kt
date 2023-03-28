package com.example.spacexapp.data.mapper

import com.example.spacexapp.data.models.PagingResponseDto
import com.example.spacexapp.data.models.crew.Doc
import com.example.spacexapp.domain.entities.CrewEntity
import com.example.spacexapp.domain.entities.LaunchesDetailEntity
import com.example.spacexapp.domain.entities.LaunchesEntity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LaunchesMapper @Inject constructor() {

    fun mapDtoDetailToEntityItem(dto: PagingResponseDto): List<LaunchesDetailEntity> {
        return dto.docs.map {
            LaunchesDetailEntity(
                name = it.name,
                flight = it.cores.find { it.flight == it.flight }?.flight ?: 0,
                date_utc = mapDate(it.dateUtc),
                success = it.success,
                id = it.id,
                large = it.links.patch.large,
                details = it.details,
                crew = it.crew
            )
        }
    }

    fun mapDtoCrewToEntityItem(dto: List<Doc>): List<CrewEntity> {
        return dto.map {
            CrewEntity(
                agency = it.agency,
                name = it.name,
                status = it.status,
                launches = it.launches.toString()
            )
        }
    }

    fun mapDtoToEntityItem(dto: PagingResponseDto): List<LaunchesEntity> {
        return dto.docs.map {
            LaunchesEntity(
                small = it.links.patch.small,
                name = it.name,
                flight = (it.cores.find { it.flight == it.flight }?.flight) ?: 0,
                date_utc = mapDateList(it.dateUtc),
                success = it.success,
                flightNumber = it.flightNumber,
                id = it.id
            )
        }
    }

    private fun mapDate(date: String): String {
        val dateObject =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault()).parse(date)
        val formatter = SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault())
        return formatter.format(dateObject)
    }

    private fun mapDateList(date: String): String {
        val dateObject =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault()).parse(date)
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return formatter.format(dateObject)
    }
}