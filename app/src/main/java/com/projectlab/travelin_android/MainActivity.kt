package com.projectlab.travelin_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController
import com.projectlab.travelin_android.presentation.navigation.AuthNavigation


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelinTheme(dynamicColor = true) {
                val navController = rememberNavController()
                AuthNavigation(navController = navController)
            }
        }
    }
}
