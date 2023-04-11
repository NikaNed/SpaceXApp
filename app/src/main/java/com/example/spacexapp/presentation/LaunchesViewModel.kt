package com.example.spacexapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.spacexapp.domain.entities.LaunchesEntity
import com.example.spacexapp.domain.usecase.GetLaunchesInfoUseCase
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(
    private val getLaunchesInfoUseCase: GetLaunchesInfoUseCase,
) : ViewModel() {

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    suspend fun getLaunchesPaging(): LiveData<PagingData<LaunchesEntity>> {
        return getLaunchesInfoUseCase.invokePaging().cachedIn(viewModelScope)
    }
}



