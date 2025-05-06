package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.Image
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

// Typography will be set up globally

@Composable
fun SearchPlaces(
    modifier: Modifier = Modifier,
    locationIcon: Painter,
    searchString: String,
    location: String
) {
    val currentText = stringResource(R.string.search_global_current, location)

    Row(modifier = modifier) {
        LocationIcon(modifier, locationIcon)
        Spacer(modifier = Modifier.width(20.dp))
        Column(modifier = Modifier.height(38.dp), verticalArrangement = Arrangement.SpaceAround) {
            SearchPlaceText(modifier, searchString)
            CurrentLocationText(modifier, currentText)
        }
    }
}

// The placeholder icon will be replaced by the official icon from the design system once it's ready to use
@Composable
fun LocationIcon(modifier: Modifier = Modifier, icon: Painter) {

    Box(modifier = modifier) {
        Image(
            painter = icon,
            contentDescription = "Location icon"
        )
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

@Preview(showBackground = true)
@Composable
fun SearchPlacesPreview() {
    SearchPlaces(
        locationIcon = painterResource(R.drawable.placeholder_map_marker_alt),
        searchString = stringResource(R.string.search_global_nearby),
        location = "Mars"
    )
}
