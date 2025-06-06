package com.projectlab.core.presentation.ui.di

import com.projectlab.core.domain.use_cases.error.ErrorMapper
import com.projectlab.core.presentation.ui.utils.ErrorMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorMapperModule {

    @Binds
    abstract fun bindErrorMapper(
        errorMapperImpl: ErrorMapperImpl
    ): ErrorMapper
}