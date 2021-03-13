package com.example.packyourtrip.di.module

import com.example.packyourtrip.ui.auth.AuthFragment
import com.example.packyourtrip.ui.checklist.actions.LeaveCheckListFragment
import com.example.packyourtrip.ui.checklist.things.ThingsCheckListFragment
import com.example.packyourtrip.ui.main.trip.TripFragment
import com.example.packyourtrip.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun bindAuthFragment(): AuthFragment

    @ContributesAndroidInjector
    abstract fun bindThingsCheckListFragment(): ThingsCheckListFragment

    @ContributesAndroidInjector
    abstract fun bindLeaveCheckListFragment(): LeaveCheckListFragment

    @ContributesAndroidInjector
    abstract fun bindTripFragment(): TripFragment
}