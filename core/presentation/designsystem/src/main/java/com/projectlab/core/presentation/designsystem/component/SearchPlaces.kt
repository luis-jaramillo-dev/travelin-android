package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.projectlab.core.presentation.designsystem.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchPlaces(
    modifier: Modifier = Modifier,
    locationIcon: @Composable (Modifier) -> Unit,
    searchString: String,
    location: String,
    onClick: () -> Unit
) {
    val currentText = stringResource(R.string.search_global_current, location)

    Row(modifier = modifier.clickable(onClick = onClick)) {
        locationIcon(modifier)
        Spacer(modifier = Modifier.width(20.dp))
        Column(verticalArrangement = Arrangement.SpaceAround) {
            SearchPlaceText(modifier, searchString)
            CurrentLocationText(modifier, currentText)
        }
    }
}

@Composable
fun SearchPlaceText(modifier: Modifier = Modifier, text: String) {
    Text(
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        text = text
    )
}

@Composable
fun CurrentLocationText(modifier: Modifier = Modifier, text: String) {
    Text(
        fontSize = 10.sp,
        fontWeight = FontWeight.W400,
        text = text
    )
}
