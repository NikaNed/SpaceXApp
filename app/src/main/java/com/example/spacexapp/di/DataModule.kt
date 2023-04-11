package com.example.spacexapp.di

import com.example.spacexapp.data.SpaceXRepositoryImpl
import com.example.spacexapp.data.api.ApiFactory
import com.example.spacexapp.data.api.ApiService
import com.example.spacexapp.domain.repository.SpaceXRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindSpaceXRepository(impl: SpaceXRepositoryImpl): SpaceXRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}

