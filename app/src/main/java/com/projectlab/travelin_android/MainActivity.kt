package com.projectlab.travelin_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import dagger.hilt.android.AndroidEntryPoint
import com.projectlab.feature.onboarding.presentation.ui.OnboardingScreen
import com.projectlab.navigation.NavigationCommand
import com.projectlab.navigation.NavigationManager
import com.projectlab.travelin_android.ui.Screens

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { padding ->
                TravelinTheme(dynamicColor = false) {
                    val navController = rememberNavController()
                    val navManager = NavigationManager(navController)

                    NavHost(
                        navController = navController,
                        startDestination = Screens.Onboarding,
                    ) {
                        composable<Screens.Onboarding> {
                            OnboardingScreen {
                                navManager.navigate(
                                    NavigationCommand.NavigateToRoute(Screens.Example)
                                )
                            }
                        }

                        composable<Screens.Example> {
                            Column(
                                modifier = Modifier
                                    .padding(padding)
                                    .padding(horizontal = 16.dp)
                            ) {
                                ExampleUI()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExampleUI() {
    Text("Hello World!")
}