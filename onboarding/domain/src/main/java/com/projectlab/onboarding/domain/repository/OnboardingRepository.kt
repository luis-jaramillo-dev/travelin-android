package com.projectlab.onboarding.domain.repository

import com.projectlab.onboarding.domain.model.OnBoarding

interface OnboardingRepository {
    fun getOnboardingPages(): List<OnBoarding>
}
