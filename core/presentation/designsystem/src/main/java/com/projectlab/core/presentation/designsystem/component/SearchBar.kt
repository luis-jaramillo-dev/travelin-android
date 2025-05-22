package com.projectlab.core.presentation.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

internal val historyMaxHeight: Dp = 250.dp

@Composable
fun SearchBarComponent(
    query: String,
    contentsDescription: String,
    placeholder: String,
    onEnter: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSearchPressed: () -> Unit,
    modifier: Modifier = Modifier,
    history: List<String> = listOf(),
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = modifier
            .zIndex(1f)
            .clip(shape = MaterialTheme.shapes.large)
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { expanded = it.isFocused }
                .semantics { contentDescription = contentsDescription },
            placeholder = { Text(text = placeholder) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { onEnter() },
            ),
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            ),
            trailingIcon = {
                TravelinIconButton(
                    onClick = onSearchPressed,
                    icon = { IconSearch(modifier = Modifier) },
                )
            },
        )

        if (history.isEmpty()) return

        AnimatedVisibility(
            visible = expanded,
            modifier = Modifier.padding(top = 56.dp),
        ) {
            // TODO find a way to render this on top and not push the elements below the search bar
            Column(
                modifier = Modifier
                    .heightIn(max = historyMaxHeight)
                    .verticalScroll(rememberScrollState()),
            ) {
                history.forEach { entry ->
                    ListItem(
                        headlineContent = { Text(entry) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onQueryChange(entry)
                                expanded = false
                            },
                    )
                }
            }
        }
    }
}
