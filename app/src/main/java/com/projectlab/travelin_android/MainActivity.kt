package com.projectlab.travelin_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.auth.presentation.AuthScreen
import com.projectlab.core.presentation.designsystem.TravelinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelinTheme(dynamicColor = true) {
                Scaffold(
                    content = { padding ->
                        Column(Modifier.padding(padding).padding(horizontal = 16.dp)) {
                            AuthScreen()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ExampleUI() {
    Text("Hello World!")
}