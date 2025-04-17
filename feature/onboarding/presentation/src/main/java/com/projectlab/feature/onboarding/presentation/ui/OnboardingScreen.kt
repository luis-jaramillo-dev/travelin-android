package com.projectlab.feature.onboarding.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.feature.onboarding.presentation.R
import com.projectlab.feature.onboarding.presentation.data.OnboardingPage
import com.projectlab.feature.onboarding.presentation.ui.theme.OnboardingTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(onNavigateToLogin: () -> Unit) {
    val pagerState = rememberPagerState { 3 }
    val scope = rememberCoroutineScope()

    val pages = listOf(
        OnboardingPage(
            title = stringResource(R.string.onboarding1_title),
            description = stringResource(R.string.onboarding_body),
            backgroundImage = painterResource(id = R.drawable.onboarding1)
        ),
        OnboardingPage(
            title = stringResource(R.string.onboarding2_title),
            description = stringResource(R.string.onboarding_body),
            backgroundImage = painterResource(id = R.drawable.onboarding2)
        ),
        OnboardingPage(
            title = stringResource(R.string.onboarding3_title),
            description = stringResource(R.string.onboarding_body),
            backgroundImage = painterResource(id = R.drawable.onboarding3)
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
                            onNavigateToLogin()
                        }
                    }
                }
            )
        }

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 35.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(pagerState.pageCount) { pageIndex ->
                val opacity = if (pagerState.currentPage == pageIndex) 1.0f else 0.34f

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(RoundedCornerShape(size = 999.dp))
                        .size(width = 15.dp, height = 6.dp)
                        .background(Color.White.copy(alpha = opacity))
                )

                if (pageIndex != pagerState.pageCount - 1) {
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingTheme {
        OnboardingScreen {}
    }
}
