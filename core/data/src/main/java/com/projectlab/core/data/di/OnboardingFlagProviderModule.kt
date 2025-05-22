package com.projectlab.core.data.di

import com.projectlab.core.data.repository.OnboardingFlagProviderImpl
import com.projectlab.core.domain.repository.OnboardingFlagProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OnboardingFlagProviderModule {
    @Binds
    @Singleton
    abstract fun bindOnboardingFlagProvider(
        onboardingFlagProviderImpl: OnboardingFlagProviderImpl,
    ): OnboardingFlagProvider
}
