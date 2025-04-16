package com.example.onboarding.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.onboarding.ui.components.OnboardingScreen
import com.projectlab.feature.onboarding.draft_jose.R

/*
@Composable
fun Onboarding3Screen(navigateToLogin:()->Unit){
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment= Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.weight(1f) ) */
/*abarcara todo el espacio sobrante*//*

        Text(text="Onboarding 3 Screen", fontSize=25.sp)
        Spacer(modifier = Modifier.weight(1f) )
        Button(onClick ={navigateToLogin( ) } ) {
            Text(text="Next")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}*/
@Composable
fun Onboarding3Screen(navigateToLogin: () -> Unit) {
    OnboardingScreen(
        backgroundImage = painterResource(id = R.drawable.onboarding3_bg),
        title = "Lets explore the\n world",
        description = "Find thousands of tourist destinations\n ready for you to visit",
        activePage = 2,
        onNextClick = { navigateToLogin() }
    )
}
