package com.projectlab.travelin_android.presentation.screens.login.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.ui.res.painterResource
import com.projectlab.auth.presentation.R

@Composable
fun LoginHeader(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = Modifier
            .width(140.dp)
            .height(140.dp),
        painter = painterResource(R.drawable.logo_filled),
        contentDescription = "Traveling logo filled"
    )
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = "Welcome to Discover",
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
    )
    Text(
        text = "Please choose your login option below",
        style = MaterialTheme.typography.bodyMedium
    )
}