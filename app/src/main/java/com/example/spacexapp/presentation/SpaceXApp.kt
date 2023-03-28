package com.example.spacexapp.presentation

import android.app.Application
import com.example.spacexapp.di.DaggerApplicationComponent

class SpaceXApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}