package com.projectlab.onboarding.data.di

import com.projectlab.onboarding.data.repository.OnboardingRepositoryImpl
import com.projectlab.onboarding.domain.repository.OnboardingRepository
import com.projectlab.onboarding.domain.usecase.GetOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {

    @Provides
    fun provideOnboardingRepository(): OnboardingRepository {
        return OnboardingRepositoryImpl()
    }

    @Provides
    fun provideGetOnboardingPagesUseCase(
        repository: OnboardingRepository
    ): GetOnboardingUseCase {
        return GetOnboardingUseCase(repository)
    }
}
