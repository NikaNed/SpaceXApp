package com.example.spacexapp.data.api

import com.example.spacexapp.data.models.*
import com.example.spacexapp.data.models.crew.CrewBody
import com.example.spacexapp.data.models.crew.CrewResponseDto
import com.example.spacexapp.data.models.detail.DetailBody
import com.example.spacexapp.data.models.launches.LaunchesBody
import retrofit2.http.*

interface ApiService {

    @POST("v4/launches/query")
    suspend fun getPagingLaunchesInfo(@Body sortInfo: LaunchesBody): PagingResponseDto

    @POST("v4/launches/query")
    suspend fun getLaunchesDetailInfo(@Body flightNumber: DetailBody): PagingResponseDto

    @POST("v4/crew/query")
    suspend fun getLaunchesCrewInfo(@Body launches: CrewBody): CrewResponseDto

}