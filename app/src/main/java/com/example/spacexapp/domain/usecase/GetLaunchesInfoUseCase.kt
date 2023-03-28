package com.example.spacexapp.domain.usecase

import com.example.spacexapp.domain.repository.SpaceXRepository
import javax.inject.Inject

class GetLaunchesInfoUseCase @Inject constructor(
    private val repository: SpaceXRepository
) {

    suspend fun invokePaging() = repository.getLaunchesPagingInfo()

}