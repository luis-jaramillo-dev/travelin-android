package com.projectlab.core.data.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.projectlab.core.data.Constants.REFERENCE_USERS
import com.projectlab.core.data.networking.FirestoreItineraryRepositoryImpl
import com.projectlab.core.data.networking.FirestoreUserRepositoryImpl
import com.projectlab.core.data.networking.UsersRepositoryImpl
import com.projectlab.core.domain.repository.ItineraryRepository
import com.projectlab.core.domain.repository.UserRepository
import com.projectlab.core.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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

    // TODO: implement: bind ItineraryRepository, FlightRepository, etc.

    companion object {

    }
    @Provides @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(REFERENCE_USERS)

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

}