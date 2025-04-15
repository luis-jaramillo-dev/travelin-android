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
import cl.travelin.onboarding.presentation.ui.components.OnboardingScreen
import cl.travelin.onboarding.R


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
fun Onboarding1Screen(navigateToOnboarding2:()->Unit){
    OnboardingScreen(
    backgroundImage = painterResource(id = R.drawable.onboarding1_bg),
        title = "Get ready for the\n next trip",
        description = "Find thousands of tourist destinations\n ready for you to visit",
        activePage = 0,
        onNextClick = {navigateToOnboarding2()}
    )
}