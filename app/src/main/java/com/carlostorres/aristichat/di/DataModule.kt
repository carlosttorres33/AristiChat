package com.carlostorres.aristichat.di

import com.carlostorres.aristichat.data.network.FirebaseChatService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabaseReference() = Firebase.database.reference

    @Provides
    @Singleton
    fun provideFirebaseService(reference: DatabaseReference) = FirebaseChatService(reference)

}