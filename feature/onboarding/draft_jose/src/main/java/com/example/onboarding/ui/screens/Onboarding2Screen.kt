package com.example.onboarding.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.onboarding.ui.components.OnboardingScreen
import com.projectlab.feature.onboarding.draft_jose.R

/*
@Composable
fun Onboarding2Screen(navigateToOnboarding3:()->Unit){
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment= Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.weight(1f) ) */
/*abarcara todo el espacio sobrante*//*

        Text(text="Onboarding 2 Screen", fontSize=25.sp)
        Spacer(modifier = Modifier.weight(1f) )
        Button(onClick ={navigateToOnboarding3( ) } ) {
            Text(text="Next")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}*/
@Composable
fun Onboarding2Screen(navigateToOnboarding3: () -> Unit) {
    OnboardingScreen(
        backgroundImage = painterResource(id = R.drawable.onboarding2_bg),
        title = "Visit tourist\n attractions",
        description = "Find thousands of tourist destinations\n ready for you to visit",
        activePage = 1,
        onNextClick = { navigateToOnboarding3() }
    )
}
