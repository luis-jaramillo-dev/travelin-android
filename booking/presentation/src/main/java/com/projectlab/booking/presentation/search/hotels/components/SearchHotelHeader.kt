package com.projectlab.booking.presentation.screens.hotels.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.projectlab.booking.presentation.screens.HotelsViewModel
import com.projectlab.booking.presentation.screens.hotels.search.SearchHotelState
import com.projectlab.core.presentation.designsystem.component.IconBack
import com.projectlab.core.presentation.designsystem.theme.spacing
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.component.IconSearch
import com.projectlab.core.presentation.designsystem.component.TravelinIconButton

@Composable
fun SearchHotelHeader(
    modifier: Modifier = Modifier,
    uiState: SearchHotelState,
    viewModel: HotelsViewModel
) {

    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(MaterialTheme.spacing.SectionSpacing)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconBack(modifier = Modifier, size = 40)
            SearchBarComponent(
                query = uiState.query,
                onEnter = {
                    if (uiState.query.isNotBlank()) {
                        viewModel.onSearchSubmitted()
                    }
                },
                modifier = Modifier,
                onQueryChange = { viewModel.onQueryChanged(it) },
                onSearchPressed = {}
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SectionSpacing))

    }
}

@Composable
fun SearchBarComponent(
    query: String,
    onEnter: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSearchPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Search City Input" },
            placeholder = { Text(text = stringResource(R.string.search_city_placeholder)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onEnter() }
            ),
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary
            ),
            trailingIcon = {
                TravelinIconButton(
                    onClick = onSearchPressed,
                    icon = { IconSearch(modifier = Modifier) })
            }
        )
    }
}