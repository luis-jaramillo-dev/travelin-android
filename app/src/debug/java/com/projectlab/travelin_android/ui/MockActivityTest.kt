package com.projectlab.travelin_android.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.projectlab.booking.presentation.ui.BookingScreen
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MockActivityTest is a test activity used to generate test data in Firestore.
 * We should used in the debug build variant.
 * It uses the BookingScreen composable to display a button to generate test data.
 *
 * @author ricardoceadev
 */

@AndroidEntryPoint
class MockActivityTest : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelinTheme(dynamicColor = false) {
                BookingScreen()
            }
        }
    }
}