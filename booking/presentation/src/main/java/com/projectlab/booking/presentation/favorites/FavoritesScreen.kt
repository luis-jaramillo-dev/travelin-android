package com.projectlab.booking.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.projectlab.booking.presentation.R
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar
import com.projectlab.core.presentation.designsystem.component.SearchBar
import com.projectlab.core.presentation.designsystem.component.VerticalFavoriteCard
import com.projectlab.core.presentation.designsystem.theme.spacing

private enum class FavoriteTabItem {
    DESTINATIONS,
    HOTELS,
}

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onHomeClick: () -> Unit,
    onTripsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onActivityClick: (id: String) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.queryFavoriteActivities()
        viewModel.queryFavoriteHotels()
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                BottomNavRoute.FAVORITES,
                onHomeClick,
                onFavoritesClick = {},
                onTripsClick,
                onProfileClick,
            )
        },
    ) { padding ->
        FavoritesScreenComponent(
            viewModel = viewModel,
            onActivityClick = onActivityClick,
            modifier = Modifier.padding(padding),
        )
    }
}

@Composable
private fun FavoritesScreenComponent(
    viewModel: FavoritesViewModel,
    onActivityClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableStateOf(FavoriteTabItem.DESTINATIONS) }
    val uiState by viewModel.uiState.collectAsState()

    val onQueryChange: (String) -> Unit = { newQuery ->
        viewModel.onQueryChange(newQuery)
    }

    val onSearchPressed: () -> Unit = {
        when (selectedTab) {
            FavoriteTabItem.DESTINATIONS -> viewModel.queryFavoriteActivities()
            FavoriteTabItem.HOTELS -> viewModel.queryFavoriteHotels()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = MaterialTheme.spacing.regular),
    ) {
        SearchBar(
            query = uiState.query,
            contentsDescription = stringResource(R.string.search_favorites_input),
            placeholder = stringResource(R.string.search_into_your_favorites),
            onEnter = onSearchPressed,
            onQueryChange = onQueryChange,
            onSearchPressed = onSearchPressed,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.semiLarge),
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.regular))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = MaterialTheme.spacing.semiLarge,
                    end = MaterialTheme.spacing.semiLarge,
                ),
        ) {
            Text(
                text = stringResource(R.string.favorites),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                FavoriteTabItem(
                    label = stringResource(R.string.destinations),
                    selected = selectedTab == FavoriteTabItem.DESTINATIONS,
                    onClick = { selectedTab = FavoriteTabItem.DESTINATIONS },
                    modifier = Modifier.weight(1f),
                )
                FavoriteTabItem(
                    label = stringResource(R.string.hotels),
                    selected = selectedTab == FavoriteTabItem.HOTELS,
                    onClick = { selectedTab = FavoriteTabItem.HOTELS },
                    modifier = Modifier.weight(1f),
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }

                return@Column
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.semiLarge),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.semiLarge),
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                when (selectedTab) {
                    FavoriteTabItem.DESTINATIONS -> {
                        items(uiState.activities) { favorite ->
                            VerticalFavoriteCard(
                                name = favorite.name,
                                description = favorite.description,
                                location = favorite.location.toString(),
                                rating = favorite.rating.toString(),
                                pictureUrl = favorite.pictures.getOrElse(0) { null },
                                isFavorite = true,
                                onFavoriteClick = { viewModel.removeFavoriteActivity(favorite.id) },
                                onClick = { onActivityClick(favorite.id) },
                                modifier = Modifier
                                    .height(MaterialTheme.spacing.favoriteCardHeight)
                                    .weight(1f)
                            )
                        }
                    }

                    FavoriteTabItem.HOTELS -> {
                        items(uiState.hotels) { hotel ->
                            VerticalFavoriteCard(
                                name = hotel.name,
                                description = hotel.amenities.toString(),
                                location = hotel.address,
                                rating = hotel.rating.toString(),
                                pictureUrl = hotel.displayImageUrl,
                                isFavorite = true,
                                onFavoriteClick = { viewModel.removeFavoriteHotel(hotel.id) },
                                onClick = { onActivityClick(hotel.id) },
                                modifier = Modifier
                                    .height(MaterialTheme.spacing.favoriteCardHeight)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FavoriteTabItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
            ),
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.small),
        )

        if (selected) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.spacing.TinySpacing)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(MaterialTheme.spacing.CornerRadius),
                    ),
            )
        }
    }
}
