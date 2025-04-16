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
fun Onboarding3Screen(navigateToLogin:()->Unit){
    OnboardingScreen(
        backgroundImage = painterResource(id = R.drawable.onboarding3_bg),
        title = "Lets explore the\n world",
        description = "Find thousands of tourist destinations\n ready for you to visit",
        activePage = 2,
        onNextClick = {navigateToLogin()}
    )
}