package com.projectlab.booking.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.projectlab.booking.presentation.R
import com.projectlab.booking.presentation.home.HomeUiState
import com.projectlab.core.presentation.designsystem.component.ButtonHotel
import com.projectlab.core.presentation.designsystem.component.ButtonOversea
import com.projectlab.core.presentation.designsystem.component.SearchBar
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun HomeSearchComponent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onQueryChange: (String) -> Unit,
    onQuerySubmitted: () -> Unit,
    onDeleteHistoryEntry: (String) -> Unit,
    onClickSearchHotel: () -> Unit
) {

    Box(modifier = Modifier.height(MaterialTheme.spacing.homeHeaderImageSize)) {
        Image(
            painter = painterResource(R.drawable.homebackground),
            contentDescription = "Home Background",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x33000000))
        )
        Column(modifier = Modifier.padding(MaterialTheme.spacing.semiLarge)) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.homeHeaderSpacer))
            Text(
                text = stringResource(R.string.exploreTheWorld),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.surfaceVariant,
                fontSize = 37.sp,
                fontWeight = FontWeight.W900
            )
            Text(
                text = stringResource(R.string.travelNextLevel),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surfaceVariant,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Box(Modifier.fillMaxWidth()) {
                SearchBar(
                    query = uiState.query,
                    contentsDescription = "Search City Input",
                    placeholder = stringResource(com.projectlab.core.presentation.designsystem.R.string.search_city_placeholder),
                    onEnter = onQuerySubmitted,
                    onQueryChange = onQueryChange,
                    onSearchPressed = onQuerySubmitted,
                    modifier = Modifier.fillMaxWidth(),
                    history = uiState.history,
                    onDeleteHistoryEntry = onDeleteHistoryEntry
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                ButtonHotel(
                    modifier = Modifier,
                    onClick = { onClickSearchHotel() },
                )
                ButtonOversea(
                    modifier = Modifier,
                    onClick = {}
                )
            }
        }
    }
}