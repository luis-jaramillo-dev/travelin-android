package com.projectlab.feature.onboarding.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.repository.OnboardingFlagProviderImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingFlagProvider: OnboardingFlagProviderImpl,
) : ViewModel() {
    fun hasOpenedAppBefore(): Boolean {
        return runBlocking {
            onboardingFlagProvider.hasOpenedAppBefore()
        }
    }

    fun setAsOpenedAppBefore() {
        viewModelScope.launch {
            onboardingFlagProvider.setAsOpenedBefore()
        }
    }
}
