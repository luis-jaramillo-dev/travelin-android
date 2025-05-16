package com.projectlab.core.data.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.projectlab.core.data.Constants.REFERENCE_USERS
import com.projectlab.core.data.repository.FirestoreActivityRepositoryImpl
import com.projectlab.core.data.repository.FirestoreFlightRepositoryImpl
import com.projectlab.core.data.repository.FirestoreFlightSegmentRepositoryImpl
import com.projectlab.core.data.repository.FirestoreHotelRepositoryImpl
import com.projectlab.core.data.repository.FirestoreItineraryRepositoryImpl
import com.projectlab.core.data.repository.FirestoreUserRepositoryImpl
import com.projectlab.core.data.repository.UsersRepositoryImpl
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.FlightRepository
import com.projectlab.core.domain.repository.FlightSegmentRepository
import com.projectlab.core.domain.repository.HotelRepository
import com.projectlab.core.domain.repository.ItineraryRepository
import com.projectlab.core.domain.repository.UserRepository
import com.projectlab.core.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * This module provides the dependencies for the data layer of the application.
 * It binds the implementations of various repositories to their respective interfaces.
 * Uses Dagger Hilt for dependency injection.
 *
 * @author ricardoceadev
 */

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindUserRepository(
        impl: FirestoreUserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindItineraryRepository(
        impl: FirestoreItineraryRepositoryImpl
    ): ItineraryRepository

    @Binds
    abstract fun bindFlightRepository(
        impl: FirestoreFlightRepositoryImpl
    ) : FlightRepository

    @Binds
    abstract fun bindFlightSegmentRepository(
        impl : FirestoreFlightSegmentRepositoryImpl
    ) : FlightSegmentRepository

    @Binds
    abstract fun bindHotelRepository(
        impl: FirestoreHotelRepositoryImpl
    ) : HotelRepository

    @Binds
    abstract fun bindActivityRepository(
        impl: FirestoreActivityRepositoryImpl
    ): ActivityRepository

    // TODO: implement: bind ItineraryRepository, FlightRepository, etc.

    companion object {

        @Provides @Singleton
        fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

        @Provides
        fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(REFERENCE_USERS)

        @Provides
        fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl


    }

}