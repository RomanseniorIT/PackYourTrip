package com.example.packyourtrip.di.module

import com.example.packyourtrip.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindMainActivity(): MainActivity
}