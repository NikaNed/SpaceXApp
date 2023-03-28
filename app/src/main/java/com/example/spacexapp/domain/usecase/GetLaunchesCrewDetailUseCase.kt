package com.example.spacexapp.domain.usecase

import com.example.spacexapp.data.models.crew.CrewBody
import com.example.spacexapp.domain.repository.SpaceXRepository
import javax.inject.Inject

class GetLaunchesCrewDetailUseCase @Inject constructor(
    private val repository: SpaceXRepository
) {
     suspend operator fun invoke(launches: CrewBody) = repository.getLaunchesCrewDetailInfo(launches)
}