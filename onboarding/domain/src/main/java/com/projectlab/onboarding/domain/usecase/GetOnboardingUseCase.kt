package com.projectlab.onboarding.domain.usecase

import com.projectlab.onboarding.domain.model.OnBoarding
import com.projectlab.onboarding.domain.repository.OnboardingRepository
import javax.inject.Inject

class GetOnboardingUseCase @Inject constructor(
    private val repository: OnboardingRepository
) {
    operator fun invoke(): List<OnBoarding> {
        return repository.getOnboardingPages()
    }
}