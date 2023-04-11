package com.example.spacexapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexapp.data.models.crew.CrewBody
import com.example.spacexapp.data.models.crew.CrewQuery
import com.example.spacexapp.data.models.detail.DetailBody
import com.example.spacexapp.data.models.detail.DetailQuery
import com.example.spacexapp.domain.entities.CrewEntity
import com.example.spacexapp.domain.entities.LaunchesDetailEntity
import com.example.spacexapp.domain.usecase.GetLaunchesCrewDetailUseCase
import com.example.spacexapp.domain.usecase.GetLaunchesDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getLaunchesDetailUseCase: GetLaunchesDetailUseCase,
    private val getLaunchesCrewDetailUseCase: GetLaunchesCrewDetailUseCase,
): ViewModel() {

    private val _detail = MutableLiveData<List<LaunchesDetailEntity>>()
    val detail: LiveData<List<LaunchesDetailEntity>>
        get() = _detail

    private val _crew = MutableLiveData<List<CrewEntity>>()
    val crew: LiveData<List<CrewEntity>>
        get() = _crew

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    fun getDetail(flightNumber: String) {
        val flight = DetailBody(
            query = DetailQuery(flightNumber)
        )
        viewModelScope.launch {
            _progressBar.value = true
            val response = getLaunchesDetailUseCase.invoke(flight)
            _detail.value = response!!
            _progressBar.value = false
        }
    }

    fun getCrew(launches: String) {
        val launches = CrewBody(
            query = CrewQuery(launches)
        )
        viewModelScope.launch {
            _progressBar.value = true
            val responseCrew = getLaunchesCrewDetailUseCase.invoke(launches)
            _crew.value = responseCrew!!
            _progressBar.value = false
        }
    }
}