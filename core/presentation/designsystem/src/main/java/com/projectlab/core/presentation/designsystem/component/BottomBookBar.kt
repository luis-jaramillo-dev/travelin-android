package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun BottomBookBar(activity: ActivityDto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.ImageSize)
            .padding(
                horizontal = MaterialTheme.spacing.ScreenHorizontalPadding,
                vertical = MaterialTheme.spacing.ScreenVerticalSpacing
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (activity.price.amount.isNotEmpty()) {
            Text(
                text =
                    stringResource(R.string.pricePerPerson, activity.price.amount),
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                text = stringResource(R.string.tour_list_card_price_not_available),
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.primary
            )
        }

        ButtonComponent(
            text = stringResource(R.string.addToItinerary),
            onClick = { },
            variant = ButtonVariant.Primary,
        )
    }
}