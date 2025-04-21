package com.projectlab.travelin_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectlab.core.presentation.desingsystem.component.Icono
import com.projectlab.travelin_android.ui.theme.TravelinTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelinTheme(dynamicColor = true) {
                Scaffold(
                    content = { padding ->
                        Column(Modifier.padding(padding).padding(horizontal = 16.dp)) {
                            ExampleUI()
                        }
                    }
                )
            }
        }
    }
}
@Preview
@Composable
fun ExampleUI() {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text("Hello World!")
        Icono()
    }

}