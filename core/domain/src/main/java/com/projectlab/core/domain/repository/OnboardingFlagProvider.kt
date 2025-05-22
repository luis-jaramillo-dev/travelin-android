package com.projectlab.core.domain.repository

/**
 * Interface for providing the onboarding flag.
 */
interface OnboardingFlagProvider {
    suspend fun hasOpenedAppBefore(): Boolean
    suspend fun setAsOpenedBefore()
}
