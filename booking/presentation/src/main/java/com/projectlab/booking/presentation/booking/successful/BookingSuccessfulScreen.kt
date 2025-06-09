package com.projectlab.booking.presentation.booking.successful

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.projectlab.booking.presentation.booking.successful.components.BookingSuccessfulContent

@Composable
fun BookingSuccessfulScreen(
    modifier: Modifier = Modifier
) {
    Scaffold {
        BookingSuccessfulContent(modifier = modifier.padding(it))
    }
}