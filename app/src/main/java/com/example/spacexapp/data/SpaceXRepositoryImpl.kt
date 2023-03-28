package com.example.spacexapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.spacexapp.data.api.ApiService
import com.example.spacexapp.data.mapper.LaunchesMapper
import com.example.spacexapp.data.models.crew.CrewBody
import com.example.spacexapp.data.models.detail.DetailBody
import com.example.spacexapp.domain.repository.SpaceXRepository
import com.example.spacexapp.domain.entities.CrewEntity
import com.example.spacexapp.domain.entities.LaunchesDetailEntity
import com.example.spacexapp.domain.entities.LaunchesEntity
import javax.inject.Inject

class SpaceXRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: LaunchesMapper
) : SpaceXRepository {

    override suspend fun getLaunchesPagingInfo(): LiveData<PagingData<LaunchesEntity>> = Pager(
        config = PagingConfig(pageSize = 10,
        enablePlaceholders = false),
        pagingSourceFactory = { LaunchesPagingDataSource(apiService, mapper) },
    ).liveData

    override suspend fun getLaunchesDetailInfo(flightNumber: DetailBody): List<LaunchesDetailEntity>? {
        return try {
            val response = apiService.getLaunchesDetailInfo(flightNumber)
            mapper.mapDtoDetailToEntityItem(response)
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            null
        }
    }

    override suspend fun getLaunchesCrewDetailInfo(launches: CrewBody): List<CrewEntity>? {
        return try {
            val response = apiService.getLaunchesCrewInfo(launches).docs
            mapper.mapDtoCrewToEntityItem(response)
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            null
        }
    }
}



