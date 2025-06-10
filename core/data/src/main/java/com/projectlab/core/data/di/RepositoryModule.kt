package com.projectlab.core.data.di

import com.projectlab.core.data.repository.HotelsRepositoryImpl
import com.projectlab.core.domain.repository.HotelsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideHotelsRepository(
        impl: HotelsRepositoryImpl
    ): HotelsRepository = impl
}