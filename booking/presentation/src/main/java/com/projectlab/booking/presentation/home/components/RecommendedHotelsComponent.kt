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
import com.projectlab.booking.presentation.R
import com.projectlab.booking.presentation.home.HomeUiState
import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.presentation.designsystem.component.VerticalFavoriteCard
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun RecommendedHotelsComponent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    location: String,
    favoriteIds: List<String>,
    onFavoriteClick: (ActivityDto) -> Unit,
    onItemClick: (String) -> Unit
) {
    if (!uiState.isLoading) {
        Column(
            modifier.padding(horizontal = MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ElementSpacing)
        ) {
            Text(
                text = stringResource(R.string.recommended_hotels_near_you),
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            if (uiState.recommendedActivities.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ElementSpacing),
                ) {
                    items(uiState.recommendedActivities) { activity ->
                        val isFavorite = favoriteIds.contains(activity.id)
                        VerticalFavoriteCard(
                            name = activity.name,
                            description = if (activity.description.isNotEmpty()) {
                                activity.description
                            } else {
                                stringResource(R.string.no_description)
                            },
                            location = location,
                            rating = activity.rating,
                            pictureUrl = activity.pictures.firstOrNull() ?: "",
                            onFavoriteClick = { onFavoriteClick(activity) },
                            isFavorite = isFavorite,
                            onClick = { onItemClick(activity.id) },
                            modifier = Modifier.width(MaterialTheme.spacing.recommendedCardWidth)
                        )
                    }
                }
            }
        }
    }
}
