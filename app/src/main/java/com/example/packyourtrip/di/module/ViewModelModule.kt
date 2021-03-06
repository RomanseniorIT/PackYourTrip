package com.example.packyourtrip.di.module

import androidx.lifecycle.ViewModel
import com.example.packyourtrip.di.annotation.ViewModelKey
import com.example.packyourtrip.ui.auth.AuthViewModel
import com.example.packyourtrip.ui.checklist.things.TripCheckListViewModel
import com.example.packyourtrip.ui.main.defaultlists.SavedListsViewModel
import com.example.packyourtrip.ui.main.trip.TripViewModel
import com.example.packyourtrip.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun authViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TripCheckListViewModel::class)
    abstract fun tripCheckListViewModel(viewModel: TripCheckListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TripViewModel::class)
    abstract fun tripViewModel(viewModel: TripViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SavedListsViewModel::class)
    abstract fun savedListsViewModel(viewModel: SavedListsViewModel): ViewModel
}
