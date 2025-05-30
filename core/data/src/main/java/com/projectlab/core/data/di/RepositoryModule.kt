package com.projectlab.core.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.data.remote.ActivityApiService
import com.projectlab.core.data.repository.ActivityRepositoryImpl
import com.projectlab.core.domain.repository.ActivityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideActivitiesRepository(
        firestore: FirebaseFirestore,
        apiService: ActivityApiService,
    ): ActivityRepository {
        return ActivityRepositoryImpl(firestore, apiService)
    }
}
