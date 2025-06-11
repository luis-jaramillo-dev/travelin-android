package com.projectlab.booking.presentation.itinerary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.core.presentation.designsystem.component.IconBus
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun ItineraryCard(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp),
    ) {
        Column (
            modifier = modifier
                .padding(start = 2.dp, top = 9.dp, end = 2.dp, bottom = 2.dp),
        ) {
            //Insert Icon from design system:
            IconBus(
                modifier = modifier
                    .size(50.dp)
            )
        }
        // Spacer to separate the icon from the text
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

        Column (
            modifier = modifier
                .height(70.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        ) {
            // Insert text for itinerary details
            Text(
                text = "Itinerary Title", // TODO: Replace with correct title
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = Bold,
            )
            Text(
                text = "Start Date",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "July 1, 2025", //TODO: Replace with correct date
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
        }
        // Spacer to separate the text from the another Text
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiHuge))

        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 40.dp, top = 29.dp, end = 2.dp, bottom = 2.dp),

            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        ) {
            // Insert another text for itinerary details
            Text(
                text = "End Date",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "July 20, 2025", //TODO: Replace with correct date
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ItineraryCardPreview() {
    ItineraryCard(
        modifier = Modifier,
        onClick = {},
    )
}