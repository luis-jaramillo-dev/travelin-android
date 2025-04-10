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

                        NavHost(
                            navController = navController,
                            startDestination = "splashscreen",
                        ) {
                            composable("splashscreen") {
                                SplashScreen(
                                    onNavigateToLogin = {
                                        navManager.navigate(
                                            NavigationCommand.NavigateToRoute("login")
                                        )
                                        navController.currentBackStack.value.forEach {
                                                entry ->
                                            Log.d(
                                                "BackStack",
                                                "Back stack entry: ${entry.destination.route}"
                                            )
                                        }
                                    }

                                )
                            }
                            composable("login") {
                                LoginScreen(
                                    onNavigateToHome = {
                                        NavigationCommand.NavigateToRoute("home")
                                    }
                                )
                            }
                            composable("home") {
                                HomeScreen(
                                    signOut = {
                                        NavigationCommand.PopUpToRoute(
                                            route = "login",
                                            inclusive = false,
                                            fallBackRoute = "splashscreen"
                                        )
                                    },
                                    signOutInclusive = {
                                        NavigationCommand.PopUpToRoute(
                                            route = "login",
                                            inclusive = true,
                                            fallBackRoute = "splashscreen"
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun SplashScreen(
    onNavigateToLogin: (Unit) -> Unit
) {
    Column {
        Text("SplashScreen")
        Button(onClick = {
            onNavigateToLogin.invoke(Unit)
        }) {
            Text("Go to Login")
        }
    }
}

@Composable
fun LoginScreen(
    onNavigateToHome: (Unit) -> Unit
) {
    Column {
        Text("LoginScreen")
        Button(onClick = {
            onNavigateToHome.invoke(Unit)
        }) {
            Text("Home")
        }
    }
}

@Composable
fun HomeScreen(
    signOut: (Unit) -> Unit,
    signOutInclusive: (Unit) -> Unit
) {
    Column {
        Text("HomeScreen")
        Button({
            signOut.invoke(Unit)
        }) {
            Text("Sign Out")
        }
        Button({
            signOutInclusive.invoke(Unit)
        }) {
            Text("Sign Out Inclusive")
        }
    }
}