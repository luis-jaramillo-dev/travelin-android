package com.projectlab.core.data.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.projectlab.core.data.Constants.REFERENCE_USERS
import com.projectlab.core.data.repository.AmadeusTokenProviderImpl
import com.projectlab.core.data.repository.FlightRepositoryImpl
import com.projectlab.core.data.repository.FlightSegmentRepositoryImpl
import com.projectlab.core.data.repository.HotelRepositoryImpl
import com.projectlab.core.data.repository.ItineraryRepositoryImpl
import com.projectlab.core.data.repository.UserRepositoryImpl
import com.projectlab.core.data.repository.OnboardingFlagProviderImpl
import com.projectlab.core.data.repository.SearchHistoryProviderImpl
import com.projectlab.core.data.repository.UserSessionProviderImpl
import com.projectlab.core.data.repository.UsersRepositoryImpl
import com.projectlab.core.domain.repository.FlightRepository
import com.projectlab.core.domain.repository.FlightSegmentRepository
import com.projectlab.core.domain.repository.HotelRepository
import com.projectlab.core.domain.repository.ItineraryRepository
import com.projectlab.core.domain.repository.OnboardingFlagProvider
import com.projectlab.core.domain.repository.SearchHistoryProvider
import com.projectlab.core.domain.repository.TokenProvider
import com.projectlab.core.domain.repository.UserRepository
import com.projectlab.core.domain.repository.UserSessionProvider
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
    @Singleton
    abstract fun bindOnboardingFlagProvider(
        onboardingFlagProviderImpl: OnboardingFlagProviderImpl,
    ): OnboardingFlagProvider

    @Binds
    @Singleton
    abstract fun bindAmadeusTokenProvider(
        amadeusTokenProviderImpl: AmadeusTokenProviderImpl,
    ): TokenProvider

    @Binds
    @Singleton
    abstract fun bindSearchHistoryProvider(
        searchHistoryProviderImpl: SearchHistoryProviderImpl,
    ): SearchHistoryProvider

    @Binds
    @Singleton
    abstract fun bindUserSessionProvider(
        searchUserSessionImpl: UserSessionProviderImpl,
    ): UserSessionProvider

    @Binds
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    abstract fun bindItineraryRepository(
        impl: ItineraryRepositoryImpl,
    ): ItineraryRepository

    @Binds
    abstract fun bindFlightRepository(
        impl: FlightRepositoryImpl,
    ): FlightRepository

    @Binds
    abstract fun bindFlightSegmentRepository(
        impl: FlightSegmentRepositoryImpl,
    ): FlightSegmentRepository

    @Binds
    abstract fun bindHotelRepository(
        impl: HotelRepositoryImpl,
    ): HotelRepository

    // TODO: implement: bind ItineraryRepository, FlightRepository, etc.

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

        @Provides
        fun provideUsersRef(db: FirebaseFirestore): CollectionReference =
            db.collection(REFERENCE_USERS)

        @Provides
        fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl
    }
}
