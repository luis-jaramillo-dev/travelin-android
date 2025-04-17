package com.projectlab.auth.data

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class LoginFirebase {
}

@Composable
fun loginButton(modifier: Modifier = Modifier){
    Button(
        modifier = Modifier.padding(top = 15.dp),
        onClick = {}
    ){
        Text("Login")
    }
}

fun onLoginButtonClick(){
//Con google analitics
//Firebase.analytics.logEvent("log_button_click", null)
}

