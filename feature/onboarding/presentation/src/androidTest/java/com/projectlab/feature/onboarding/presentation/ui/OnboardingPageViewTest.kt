package com.projectlab.feature.onboarding.presentation.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.projectlab.feature.onboarding.presentation.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OnboardingPageViewTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

//    @get:Rule
//    val composeTestRule = createComposeRule() // TODO: Fix this to use the correct rule

    // TODO: Tests needs to be checked
    @Test
    fun shows_title_and_Description_and_button_invokes_callback() {
        var clicked = false

        // 1) We assemble the composable with test data
        composeTestRule.setContent {
            OnboardingPageView(
                page = OnboardingPage(
                    title = "Test title",
                    description = "Test Description",
                    backgroundImage = painterResource(id = R.drawable.onboarding1)
                ),
                onNextClicked = { clicked = true }
            )
        }

        // 2) We verify that the title and description are on the screen
        composeTestRule
            .onNodeWithText("Test title")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Test Description")
            .assertIsDisplayed()

        // 3) We press the button and check that the callback changed the variable
        composeTestRule
            .onNodeWithTag("onboardingNextButton")
            .performClick()

        assert(clicked)
    }

}