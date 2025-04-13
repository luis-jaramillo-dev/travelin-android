package com.projectlab.travelin_android

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projectlab.navigation.NavigationCommand
import com.projectlab.navigation.NavigationManager
import com.example.presentation.*

class MainActivity : ComponentActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    MaterialTheme {
                        val navController = rememberNavController()
                        val navManager = NavigationManager(navController = navController)

                        // This is a basic navigation host example for the app
                        // It uses a NavHost to manage the navigation between different screens
                        // TODO: Remove and implement your own navigation
                        NavHost(
                            navController = navController,
                            startDestination = "ScreenOnboarding1", // usar "splashscreen" cuando probemos de "forma real"
                        ) {
                            composable ("ScreenOnboarding1") {
                                ScreenOnboarding1(modifier = Modifier)
                            }

                        }
                    }
                }
            }

        }
    }
}

@Composable
fun ScreenOnboarding1(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Onboarding1Screen()
    }
}
