package com.projectlab.core.data.di

import com.projectlab.core.data.repository.ActivitiesRepositoryImpl
import com.projectlab.core.domain.repository.ActivitiesRepository
import com.projectlab.core.data.remote.ActivitiesApiService
import com.projectlab.core.data.remote.hotels.HotelsApiService
import com.projectlab.core.data.repository.HotelsRepositoryImpl
import com.projectlab.core.data.repository.UsersRepositoryImpl
import com.projectlab.core.domain.repository.HotelsRepository
import com.projectlab.core.domain.repository.TokenProvider
import com.projectlab.core.domain.repository.UsersRepository
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
        return ActivitiesRepositoryImpl(apiService)
    }

    @Provides
    fun provideHotelsRepository(
        impl: HotelsRepositoryImpl
    ): HotelsRepository = impl

}