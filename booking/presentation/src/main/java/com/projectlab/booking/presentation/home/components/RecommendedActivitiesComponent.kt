package com.projectlab.booking.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.projectlab.booking.presentation.R
import com.projectlab.booking.presentation.home.HomeUiState
import com.projectlab.core.presentation.designsystem.component.VerticalFavoriteCard
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun RecommendedActivitiesComponent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    location: String,
    onFavoriteClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = stringResource(R.string.recommended_activities_near_you),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W600,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        )
        if (uiState.recommendedActivities.isNotEmpty()) {
            LazyRow(
                modifier.height(MaterialTheme.spacing.recommendedCardHeight),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ElementSpacing)
            ) {
                items(uiState.recommendedActivities) { activity ->
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
                        onFavoriteClick = onFavoriteClick,
                        onClick = { onItemClick(activity.id) },
                        modifier = Modifier.width(MaterialTheme.spacing.recommendedCardWidth)
                    )
                }
            }
        }
    }
}
