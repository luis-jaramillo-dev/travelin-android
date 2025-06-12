package com.projectlab.booking.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.projectlab.booking.models.HotelUi
import com.projectlab.booking.models.toHotelUi
import com.projectlab.booking.presentation.R
import com.projectlab.booking.presentation.home.HomeUiState
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.presentation.designsystem.component.VerticalFavoriteCard
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun RecommendedHotelsComponent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    favoriteIds: List<String>,
    onFavoriteClick: (Hotel) -> Unit,
    onItemClick: (HotelUi) -> Unit
) {
    if (!uiState.isLoading) {
        Column(
            modifier.padding(horizontal = MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ElementSpacing)
        ) {
            Text(
                text = stringResource(R.string.recommended_hotels),
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            if (uiState.recommendedHotels.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ElementSpacing),
                ) {
                    items(uiState.recommendedHotels) { hotel ->
                        val isFavorite = favoriteIds.contains(hotel.id)
                        VerticalFavoriteCard(
                            name = hotel.name,
                            description = hotel.amenities.toString(),
                            location = hotel.location.address,
                            rating = hotel.rating.overallRating.toString(),
                            pictureUrl = hotel.displayImageUrl,
                            onFavoriteClick = {onFavoriteClick(hotel)},
                            isFavorite = isFavorite,
                            onClick = { onItemClick(hotel.toHotelUi()) },
                            modifier = Modifier.width(MaterialTheme.spacing.recommendedCardWidth)
                        )
                    }
                }
            }
        }
    }
}
