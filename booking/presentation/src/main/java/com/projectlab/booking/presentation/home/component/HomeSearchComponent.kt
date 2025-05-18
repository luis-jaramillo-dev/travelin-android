package com.projectlab.booking.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.projectlab.booking.presentation.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun HomeSearchComponent(
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier.height(MaterialTheme.spacing.homeHeaderImageSize)) {
        Image(
            painter = painterResource(R.drawable.homebackground),
            contentDescription = "Home Background"
        )
    }
}