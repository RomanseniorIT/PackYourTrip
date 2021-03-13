package com.example.packyourtrip.di.component

import android.app.Application
import com.example.packyourtrip.MyApp
import com.example.packyourtrip.di.module.ActivityModule
import com.example.packyourtrip.di.module.AppModule
import com.example.packyourtrip.di.module.ContextModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ContextModule::class,
    ActivityModule::class,
    AppModule::class
])
@Singleton
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}