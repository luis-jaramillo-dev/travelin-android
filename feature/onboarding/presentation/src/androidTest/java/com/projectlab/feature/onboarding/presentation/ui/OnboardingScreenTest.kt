package com.projectlab.feature.onboarding.presentation.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue

@RunWith(AndroidJUnit4::class)
class OnboardingScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    //    @get:Rule
    //    val composeTestRule = createComposeRule() // TODO: Fix this to use the correct rule

    // TODO: Test needs to be checked
    @Test
    fun onboardingScreen_navigatesOnLastPage() {
        var navigated = false

        composeTestRule.setContent {
            OnboardingScreen(
                onNavigateToLogin = { navigated = true }
            )
        }

        // Simulate 3 clicks (for 3 pages)
        repeat(3) {
            composeTestRule.onNodeWithTag("OnboardingNextButton").performClick()
        }

        assertTrue(navigated)

    }

}