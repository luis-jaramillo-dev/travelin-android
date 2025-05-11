package com.projectlab.core.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.data.networking.FirestoreUserRepositoryImpl
import com.projectlab.core.data.networking.FirestoreItineraryRepositoryImpl
import com.projectlab.core.domain.repository.UserRepository
import com.projectlab.core.domain.repository.ItineraryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(
        impl: FirestoreUserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindItineraryRepository(
        impl: FirestoreItineraryRepositoryImpl
    ): ItineraryRepository

    // TODO: implement: bind ItineraryRepository, FlightRepository, etc.
}

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {

    @Provides @Singleton
    fun provideFirestore() : FirebaseFirestore = FirebaseFirestore.getInstance()

}