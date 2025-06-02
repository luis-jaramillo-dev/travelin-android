package com.projectlab.booking.presentation.screens.hotels.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.projectlab.booking.presentation.screens.HotelsViewModel
import com.projectlab.booking.presentation.screens.hotels.search.HotelSearchUiState
import com.projectlab.booking.presentation.search.hotels.components.HotelCard
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.component.ButtonComponent
import com.projectlab.core.presentation.designsystem.component.ButtonVariant
import com.projectlab.core.presentation.designsystem.component.ImageCardWithFavorite
import com.projectlab.core.presentation.designsystem.component.StarRatingBar
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun HotelSearchContent(
    modifier: Modifier = Modifier,
    uiState: HotelSearchUiState,
    viewModel: HotelsViewModel,
    navController: NavHostController
) {

    SearchHotelsResultsComponent(
        uiState = uiState,
        navController = navController,
        onShowAllResults = {
            viewModel.showAllResults()
        },
        viewModel = viewModel
    )

}


@Composable
fun SearchHotelsResultsComponent(
    modifier: Modifier = Modifier,
    uiState: HotelSearchUiState,
    onShowAllResults: () -> Unit,
    navController: NavHostController,
    viewModel: HotelsViewModel
) {
    val hotels = uiState.hotels
    val showAll = uiState.showAllResults

    if (hotels.isNotEmpty()) {
        val itemsToShow = if (showAll) hotels else hotels.take(3)
        val restSize = hotels.size - 3

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(itemsToShow) {

                HotelCard(
                    hotel = it,
                    modifier = Modifier.fillMaxWidth(),
                    onPress = {
                        navController.navigate("hotelDetail/${it.id}")
                    },
                    viewModel = viewModel
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (!showAll && restSize > 0) {
                item {
                    ButtonComponent(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.small)
                            .fillMaxWidth(),
                        text = stringResource(R.string.show_more_available, restSize),
                        onClick = onShowAllResults,
                        variant = ButtonVariant.Outline,
                    )
                }
            }
        }
    }
}





@Composable
fun PriceText(price: String?) {
    if (price == null || price == "") {
        Text(
            text = stringResource(R.string.tour_list_card_price_not_available),
            fontSize = 12.sp,
            fontWeight = FontWeight.W400
        )
    } else {
        val fromText = stringResource(R.string.tour_list_card_price_1, price)
        val personText = stringResource(R.string.tour_list_card_price_2)
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600
                    )
                ) {
                    append(fromText)
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W400
                    )
                ) {
                    append(personText)
                }
            }
        )
    }
}

