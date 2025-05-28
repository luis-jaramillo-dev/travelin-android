package com.projectlab.core.data.di

import com.projectlab.core.data.repository.SearchHistoryProviderImpl
import com.projectlab.core.domain.repository.SearchHistoryProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchHistoryProviderModule {
    @Binds
    @Singleton
    abstract fun bindSearchHistoryProvider(
        searchHistoryProviderImpl: SearchHistoryProviderImpl
    ): SearchHistoryProvider
}
