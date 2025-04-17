package com.projectlab.feature.onboarding.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.feature.onboarding.presentation.R
import com.projectlab.feature.onboarding.presentation.data.OnboardingPage
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState { 3 }
    val scope = rememberCoroutineScope()

    val pages = listOf(
        OnboardingPage(
            title = "Get ready for the next trip",
            description = "Find thousands of tourist destinations ready for you to visit",
            imageRes = painterResource(id = R.drawable.onboarding1)
        ),
        OnboardingPage(
            title = "Visit tourist attractions",
            description = "Find thousands of tourist destinations ready for you to visit",
            imageRes = painterResource(id = R.drawable.onboarding2)
        ),
        OnboardingPage(
            title = "Lets explore the world",
            description = "Find thousands of tourist destinations ready for you to visit",
            imageRes = painterResource(id = R.drawable.onboarding3)
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
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

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(RoundedCornerShape(size = 999.dp))
                        .background(color)
                        .size(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}
