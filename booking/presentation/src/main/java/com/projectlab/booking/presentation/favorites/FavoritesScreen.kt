package com.projectlab.booking.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectlab.booking.presentation.R
import com.projectlab.core.presentation.designsystem.component.BackIconButton
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar
import com.projectlab.core.presentation.designsystem.component.SearchBar
import com.projectlab.core.presentation.designsystem.theme.bodyFontFamily
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
) {
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
            modifier = Modifier.padding(padding),
        )
    }
}

@Composable
private fun FavoritesScreenComponent(
    viewModel: FavoritesViewModel,
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
            // TODO
            FavoriteTabItem.HOTELS -> {}
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BackIconButton(
                modifier = Modifier
                    .size(MaterialTheme.spacing.IconSize),
                // TODO
                onClick = {},
            )

            Spacer(modifier = Modifier.width(10.dp))

            SearchBar(
                query = uiState.query,
                contentsDescription = stringResource(R.string.search_favorites_input),
                placeholder = stringResource(R.string.search_into_your_favorites),
                onEnter = onSearchPressed,
                onQueryChange = onQueryChange,
                onSearchPressed = onSearchPressed,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
        ) {
            Text(
                text = stringResource(R.string.favorites),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = bodyFontFamily,
                    fontWeight = FontWeight.SemiBold,
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(40.dp))

            // TODO put cards grid here
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
                .padding(vertical = 8.dp)
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
