package com.example.spacexapp.domain.usecase

import com.example.spacexapp.data.models.detail.DetailBody
import com.example.spacexapp.domain.repository.SpaceXRepository
import javax.inject.Inject

class GetLaunchesDetailUseCase @Inject constructor(
    private val repository: SpaceXRepository
) {
     suspend operator fun invoke(flightNumber: DetailBody) = repository.getLaunchesDetailInfo(flightNumber)
}