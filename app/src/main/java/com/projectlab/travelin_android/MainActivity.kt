package com.projectlab.travelin_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
/*import com.projectlab.core.presentation.designsystem.BadgeInfo
import com.projectlab.core.presentation.designsystem.BadgeOutline
import com.projectlab.core.presentation.designsystem.BadgePriceUnit
import com.projectlab.core.presentation.designsystem.ButtonOutline
import com.projectlab.core.presentation.designsystem.ButtonPrimary
import com.projectlab.core.presentation.designsystem.ButtonSecondary
import com.projectlab.core.presentation.designsystem.ButtonTertiary
import com.projectlab.core.presentation.designsystem.GradientBackground*/
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import dagger.hilt.android.AndroidEntryPoint
//import com.projectlab.travelin_android.flight.DatePickerTestScreen
//import flight.FlightScreen
import com.projectlab.travelin_android.flight.FlightScreen2
import com.projectlab.travelin_android.flight.FlightScreenContainer
//import com.projectlab.travelin_android.flight.TestDatePickerScreen
import com.projectlab.travelin_android.navigation.NavigationRoot

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelinTheme(dynamicColor = false) {
                Scaffold(
                    content = { padding ->
                        Column(Modifier
                            .padding(padding)
                            .padding(horizontal = 16.dp)) {
                            /*val navController = rememberNavController()
                            NavigationRoot(
                                navController = navController
                            )*/
                            //ExampleUI()
                           FlightScreenContainer(
                           )
                        }

                        })
                    }


               /* Surface(modifier = Modifier.fillMaxSize()) {
                    DatePickerTestScreen()
                }*/

        }
    }
}

