package com.projectlab.travelin_android

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import dagger.hilt.android.AndroidEntryPoint
import com.projectlab.travelin_android.navigation.NavigationRoot
import com.projectlab.booking.presentation.ui.BookingScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelinTheme(dynamicColor = false) {
                val navController = rememberNavController()
                BookingScreen()

                // TODO: Uncomment this when the navigation is ready
//                NavigationRoot(
//                    navController = navController
//                )
            }
        }
    }
}

