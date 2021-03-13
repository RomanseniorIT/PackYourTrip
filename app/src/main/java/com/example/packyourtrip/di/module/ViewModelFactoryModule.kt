package com.example.packyourtrip.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.packyourtrip.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        (RepositoryModule::class),
        (ViewModelModule::class)
    ]
)
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}