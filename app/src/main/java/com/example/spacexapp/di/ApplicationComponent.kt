package com.example.spacexapp.di

import android.app.Application
import com.example.spacexapp.presentation.LaunchesDetailFragment
import com.example.spacexapp.presentation.LaunchesFragment
import com.example.spacexapp.presentation.SpaceXApp
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(fragment: LaunchesFragment)
    fun inject(fragment: LaunchesDetailFragment)
    fun inject(application: SpaceXApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
