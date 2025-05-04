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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projectlab.core.presentation.designsystem.BadgeInfo
import com.projectlab.core.presentation.designsystem.BadgeOutline
import com.projectlab.core.presentation.designsystem.BadgePriceUnit
import com.projectlab.core.presentation.designsystem.ButtonOutline
import com.projectlab.core.presentation.designsystem.ButtonPrimary
import com.projectlab.core.presentation.designsystem.ButtonSecondary
import com.projectlab.core.presentation.designsystem.ButtonTertiary
import com.projectlab.core.presentation.designsystem.GradientBackground
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.core.presentation.ui.components.BottomLocationBar
import com.projectlab.core.presentation.ui.components.LocationPermissionComponent
import com.projectlab.core.presentation.ui.utils.LocationUtils
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.projectlab.feature.onboarding.presentation.ui.OnboardingScreen
import com.projectlab.navigation.NavigationCommand
import com.projectlab.navigation.NavigationManager
import com.projectlab.travelin_android.ui.Screens

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("RestrictedApi")
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
                            val locationViewModel: LocationViewModel = viewModel()
                            val context = LocalContext.current
                            val locationUtils = remember { LocationUtils(context) }
                            val showBottomBar = remember { mutableStateOf(false) }
                            Column(
                                modifier = Modifier
                                    .padding(padding)
                                    .padding(horizontal = 16.dp)
                            ) {
                                LocationPermissionComponent(
                                    locationUtils = locationUtils,
                                    viewModel = locationViewModel,
                                    context = context,
                                    showBottomBar = showBottomBar
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
fun ExampleUI() {
    Text("Hello World!")
}

@Preview(showBackground = true)
@Composable
fun GradientPreview() {
    TravelinTheme(dynamicColor = false, darkTheme = false) {
        GradientBackground {
            Text("¡Hola, quiuboles!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPrimaryPreview() {
    TravelinTheme(dynamicColor = false, darkTheme = false) {
        ButtonPrimary(
            text = "Primary Button",
            onClick = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ButtonSecondaryPreview() {
    TravelinTheme(dynamicColor = false, darkTheme = false) {
        ButtonSecondary(
            text = "Secondary Button",
            onClick = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ButtonTertiaryPreview() {
    TravelinTheme(dynamicColor = false, darkTheme = false) {
        ButtonTertiary(
            text = "Tertiary Button",
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonOutlinePreview() {
    TravelinTheme(dynamicColor = false, darkTheme = false) {
        ButtonOutline(
            text = "Outline Button",
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BadgeOutlinePreview() {
    TravelinTheme(dynamicColor = false, darkTheme = false) {
        BadgeOutline("2 day 1 night")
    }
}

@Preview(showBackground = true)
@Composable
fun BadgePriceUnitPreview() {
    TravelinTheme(dynamicColor = false, darkTheme = false) {
        BadgePriceUnit("$200", "2 person")
    }
}

@Preview(showBackground = true)
@Composable
fun BadgeInfoPreview() {
    TravelinTheme(dynamicColor = false, darkTheme = false) {
        BadgeInfo(title = "2 day 1 night", subtitle = "Duration", icon = Icons.Filled.Place)
    }
}