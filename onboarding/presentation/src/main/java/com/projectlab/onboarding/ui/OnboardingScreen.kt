package com.projectlab.onboarding.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.projectlab.onboarding.util.getImageResIdByName
import com.projectlab.onboarding.viewmodel.OnboardingViewModel


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    val pages = viewModel.pages
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val pageData = pages[page]
            val resId = getImageResIdByName(pageData.imageRes)

            OnboardingPageView(
                title = pageData.title,
                description = pageData.description,
                imageResId = resId,
                onNextClicked = {
                    scope.launch {
                        if (page < pages.lastIndex) {
                            pagerState.animateScrollToPage(page + 1)
                        } else {
                            println("Finished onboarding")
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