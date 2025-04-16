package com.projectlab.onboarding.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.projectlab.feature.onboarding.draft_tania.R
import com.projectlab.onboarding.presentation.data.OnboardingPage
import com.projectlab.onboarding.presentation.ui.ui.theme.TravelinandroidTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen() {
    TravelinandroidTheme {
        Onboarding()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding() {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val pages = listOf(
        OnboardingPage(
            title = "Get ready for the next trip",
            description = "Find thousands of tourist destinations ready for you to visit",
            imageRes = painterResource(id = R.drawable.onb1)
        ),
        OnboardingPage(
            title = "Visit tourist attractions",
            description = "Find thousands of tourist destinations ready for you to visit",
            imageRes = painterResource(id = R.drawable.onb2)
        ),
        OnboardingPage(
            title = "Lets explore the world",
            description = "Find thousands of tourist destinations ready for you to visit",
            imageRes = painterResource(id = R.drawable.onb3)
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            OnboardingPageView(
                page = pages[page],
                onNextClicked = {
                    scope.launch {
                        if (page < pages.lastIndex) {
                            pagerState.animateScrollToPage(page + 1)
                        } else {
                            println("finish onboarding")
                        }
                    }
                }
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .navigationBarsPadding(),
            activeColor = Color.White,
            inactiveColor = Color.Gray.copy(alpha = 0.4f),
            indicatorWidth = 16.dp,
            indicatorHeight = 4.dp,
            spacing = 6.dp,
            indicatorShape = RoundedCornerShape(2.dp)
        )

    }
}
