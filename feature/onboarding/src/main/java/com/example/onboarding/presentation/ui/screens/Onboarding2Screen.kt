package cl.travelin.onboarding.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import cl.travelin.onboarding.R
import cl.travelin.onboarding.presentation.ui.components.OnboardingScreen

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
fun Onboarding2Screen(navigateToOnboarding3:()->Unit){
    OnboardingScreen(
        backgroundImage = painterResource(id = R.drawable.onboarding2_bg),
        title = "Visit tourist\n attractions",
        description = "Find thousands of tourist destinations\n ready for you to visit",
        activePage = 1,
        onNextClick = {navigateToOnboarding3()}
    )
}