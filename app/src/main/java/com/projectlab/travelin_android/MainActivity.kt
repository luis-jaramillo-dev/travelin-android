package com.projectlab.travelin_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import dagger.hilt.android.AndroidEntryPoint
import com.projectlab.travelin_android.navigation.NavigationRoot

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelinTheme(dynamicColor = false) {
                val navController = rememberNavController()
                NavigationRoot(
                    navController = navController
                )
            }
        }
    }
}

