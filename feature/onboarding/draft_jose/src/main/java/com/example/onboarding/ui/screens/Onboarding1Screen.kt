package com.example.onboarding.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.onboarding.ui.components.OnboardingScreen
import com.projectlab.feature.onboarding.draft_jose.R

//@Composable
//fun Onboarding1Screen(navigateToOnboarding2:()->Unit){
//    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment= Alignment.CenterHorizontally){
//        Spacer(modifier = Modifier.weight(1f) )
//
//        Text(text="Onboarding 1 Screen", fontSize=25.sp)
//        Spacer(modifier = Modifier.weight(1f) )
//        Button(onClick ={navigateToOnboarding2( ) } ) {
//            Text(text="Next")
//        }
//        Spacer(modifier = Modifier.weight(1f))
//    }
//}

@Composable
fun Onboarding1Screen(navigateToOnboarding2: () -> Unit) {
    OnboardingScreen(
        backgroundImage = painterResource(id = R.drawable.onboarding1_bg),
        title = "Get ready for the\n next trip",
        description = "Find thousands of tourist destinations\n ready for you to visit",
        activePage = 0,
        onNextClick = { navigateToOnboarding2() }
    )
}
