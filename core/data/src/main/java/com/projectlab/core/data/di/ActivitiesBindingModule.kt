package com.projectlab.core.data.di

import com.projectlab.core.data.usecase.GetActivitiesUseCaseImpl
import com.projectlab.core.data.usecase.GetActivityUseCaseImpl
import com.projectlab.core.domain.use_cases.activities.GetActivitiesUseCase
import com.projectlab.core.domain.use_cases.activities.GetActivityUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ActivitiesBindingModule {

    @Binds
    abstract fun bindGetActivitiesUseCase(
        impl: GetActivitiesUseCaseImpl
    ): GetActivitiesUseCase

    @Binds
    abstract fun bindGetActivityUseCase(
        impl: GetActivityUseCaseImpl
    ): GetActivityUseCase

}