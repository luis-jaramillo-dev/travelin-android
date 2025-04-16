package cl.travelin.onboarding.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.travelin.onboarding.core.navigation.Onboarding1
import cl.travelin.onboarding.core.navigation.Splash
import com.airbnb.lottie.compose.LottieCompositionSpec
import kotlinx.coroutines.delay
import com.airbnb.lottie.compose.rememberLottieComposition
import cl.travelin.onboarding.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState


@Composable
fun SplashScreen(navController: NavHostController) {
    val isDarkTheme = isSystemInDarkTheme()

    val logo1s = if (isDarkTheme){
        LottieCompositionSpec.RawRes(R.raw.logo1s_dark)
    }else{
        LottieCompositionSpec.RawRes(R.raw.logo1s_light)
    }
    val composition by rememberLottieComposition(logo1s)

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )
    var visible by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(5000L)
        visible = false
        delay(500L)
        navController.navigate(Onboarding1) {
            popUpTo(Splash) { inclusive = true }
        }
    }
    AnimatedVisibility(
        visible = visible,
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f)) /*abarcara todo el espacio sobrante*/
            LottieAnimation(
                progress = { progress },
                modifier = Modifier.size(200.dp),
                composition = composition
            )

            //Text(text = "Splash Screen\n cargando... ", fontSize = 25.sp)
            //Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}