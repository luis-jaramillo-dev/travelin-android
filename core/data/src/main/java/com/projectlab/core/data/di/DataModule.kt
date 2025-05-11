package com.projectlab.core.data.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.projectlab.core.data.Constants.REFERENCE_USERS
import com.projectlab.core.data.networking.UsersRepositoryImpl
import com.projectlab.core.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(REFERENCE_USERS)

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

}