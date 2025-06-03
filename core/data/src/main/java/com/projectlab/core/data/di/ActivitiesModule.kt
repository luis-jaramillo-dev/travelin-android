package com.projectlab.core.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.data.remote.ActivityApiService
import com.projectlab.core.data.repository.ActivityRepositoryImpl
import com.projectlab.core.data.usecase.IsFavoriteActivityUseCaseImpl
import com.projectlab.core.data.usecase.QueryFavoriteActivitiesUseCaseImpl
import com.projectlab.core.data.usecase.RemoveFavoriteActivityByIdUseCaseImpl
import com.projectlab.core.data.usecase.SaveFavoriteActivityUseCaseImpl
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.use_cases.activities.IsFavoriteActivityUseCase
import com.projectlab.core.domain.use_cases.activities.QueryFavoriteActivitiesUseCase
import com.projectlab.core.domain.use_cases.activities.RemoveFavoriteActivityByIdUseCase
import com.projectlab.core.domain.use_cases.activities.SaveFavoriteActivityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ActivitiesModule {
    @Provides
    fun provideActivitiesRepository(
        firestore: FirebaseFirestore,
        apiService: ActivityApiService,
    ): ActivityRepository {
        return ActivityRepositoryImpl(firestore, apiService)
    }

    @Provides
    fun provideQueryFavoriteActivitiesUseCase(
        impl: QueryFavoriteActivitiesUseCaseImpl,
    ): QueryFavoriteActivitiesUseCase = impl

    @Provides
    fun provideIsFavoriteActivityUseCase(
        impl: IsFavoriteActivityUseCaseImpl,
    ): IsFavoriteActivityUseCase = impl

    @Provides
    fun provideSaveFavoriteActivityUseCase(
        impl: SaveFavoriteActivityUseCaseImpl,
    ): SaveFavoriteActivityUseCase = impl

    @Provides
    fun provideRemoveFavoriteActivityByIdUseCase(
        impl: RemoveFavoriteActivityByIdUseCaseImpl,
    ): RemoveFavoriteActivityByIdUseCase = impl
}
