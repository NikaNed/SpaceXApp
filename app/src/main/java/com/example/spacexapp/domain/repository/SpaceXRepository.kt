package com.example.spacexapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.spacexapp.data.models.crew.CrewBody
import com.example.spacexapp.data.models.detail.DetailBody
import com.example.spacexapp.domain.entities.CrewEntity
import com.example.spacexapp.domain.entities.LaunchesDetailEntity
import com.example.spacexapp.domain.entities.LaunchesEntity

interface SpaceXRepository {

   suspend fun getLaunchesPagingInfo(): LiveData<PagingData<LaunchesEntity>>

   suspend fun getLaunchesDetailInfo(flightNumber: DetailBody): List<LaunchesDetailEntity>?

   suspend fun getLaunchesCrewDetailInfo(launches: CrewBody): List<CrewEntity>?

}