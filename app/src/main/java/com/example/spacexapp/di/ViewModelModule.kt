package com.example.spacexapp.di

import androidx.lifecycle.ViewModel
import com.example.spacexapp.presentation.DetailViewModel
import com.example.spacexapp.presentation.LaunchesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(LaunchesViewModel::class)
    fun bindLaunchesViewModel(viewModel: LaunchesViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(DetailViewModel::class)
    fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

}