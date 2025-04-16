package cl.travelin.onboarding.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(navigateToOnboarding1:()->Unit){
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment= Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.weight(1f) ) /*abarcara todo el espacio sobrante*/
        Text(text="Login Screen", fontSize=25.sp)
        Spacer(modifier = Modifier.weight(1f) )
        Button(onClick ={navigateToOnboarding1( ) } ) {
            Text(text="Ir al principio")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}