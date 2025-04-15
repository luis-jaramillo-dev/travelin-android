package com.projectlab.onboarding.data.repository

import com.projectlab.onboarding.domain.model.OnBoarding
import com.projectlab.onboarding.domain.repository.OnboardingRepository

class OnboardingRepositoryImpl : OnboardingRepository {
    override fun getOnboardingPages(): List<OnBoarding> {
        return listOf(
            OnBoarding(
                title = "Get ready for the next trip",
                description = "Find thousands of tourist destinations ready for you to visit",
                imageRes = "onb1"
            ),
            OnBoarding(
                title = "Visit tourist attractions",
                description = "Find thousands of tourist destinations ready for you to visit",
                imageRes = "onb2"
            ),
            OnBoarding(
                title = "Let's explore the world",
                description = "Find thousands of tourist destinations ready for you to visit",
                imageRes = "onb3"
            )
        )
    }
}