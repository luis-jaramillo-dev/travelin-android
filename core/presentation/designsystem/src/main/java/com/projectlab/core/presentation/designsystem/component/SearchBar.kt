package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

internal val historyMaxHeight: Dp = 270.dp

@Composable
fun SearchBar(
    query: String,
    contentsDescription: String,
    placeholder: String,
    onEnter: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSearchPressed: () -> Unit,
    modifier: Modifier = Modifier,
    history: List<String> = listOf(),
) {
    var boxSize by remember { mutableStateOf(Size.Zero) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.large)
            .background(color = MaterialTheme.colorScheme.background)
            .onGloballyPositioned { layoutCoordinates ->
                boxSize = layoutCoordinates.size.toSize()
            },
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
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
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        keyboardController?.show()
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                expanded = !expanded
                            }
                        }
                    }
                }
        )

        if (history.isEmpty()) return

        // TODO find a way to type on the search bar while this is expanded
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = MaterialTheme.shapes.large,
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .heightIn(max = historyMaxHeight)
                .width(with(LocalDensity.current) { boxSize.width.toDp() }),
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
