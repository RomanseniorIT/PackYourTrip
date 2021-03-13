package com.example.packyourtrip

import com.chibatching.kotpref.Kotpref
import com.example.packyourtrip.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(baseContext)
    }
}