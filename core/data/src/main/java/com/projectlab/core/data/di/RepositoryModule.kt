package com.projectlab.core.data.di

import com.projectlab.core.data.repository.ActivitiesRepositoryImpl
import com.projectlab.core.domain.repository.ActivitiesRepository
import com.projectlab.core.data.repository.ActivitiesApiService
import com.projectlab.core.domain.repository.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideActivitiesRepository(
        apiService: ActivitiesApiService,
        tokenProvider: TokenProvider
    ): ActivitiesRepository {
        return ActivitiesRepositoryImpl(apiService, tokenProvider)
    }
}