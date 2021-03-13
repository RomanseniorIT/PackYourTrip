package com.example.packyourtrip.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(): FirebaseFirestore {
        return  Firebase.firestore
    }

    @Singleton
    @Provides
    fun provideAuth(): FirebaseAuth {
        return  Firebase.auth
    }
}