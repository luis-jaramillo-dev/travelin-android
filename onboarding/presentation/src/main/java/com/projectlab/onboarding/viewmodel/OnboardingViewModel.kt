package com.projectlab.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import com.projectlab.onboarding.domain.model.OnBoarding
import com.projectlab.onboarding.domain.usecase.GetOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getOnboardingPagesUseCase: GetOnboardingUseCase
) : ViewModel() {

    val pages: List<OnBoarding> = getOnboardingPagesUseCase()
}