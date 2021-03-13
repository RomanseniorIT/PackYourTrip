package com.example.packyourtrip.di.module

import androidx.lifecycle.ViewModel
import com.example.packyourtrip.di.annotation.ViewModelKey
import com.example.packyourtrip.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel
}