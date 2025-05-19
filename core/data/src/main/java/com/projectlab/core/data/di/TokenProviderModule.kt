package com.projectlab.core.data.di

import com.projectlab.core.data.repository.AmadeusTokenProviderImpl
import com.projectlab.core.domain.repository.TokenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class TokenProviderModule {

        @Binds
        @Singleton
        abstract fun bindTokenProvider(
            amadeusTokenProviderImpl: AmadeusTokenProviderImpl
        ): TokenProvider
    }