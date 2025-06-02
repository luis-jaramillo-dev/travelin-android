package com.projectlab.booking.presentation.booking.hotels.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.projectlab.core.domain.model.Price
import com.projectlab.core.presentation.designsystem.component.ButtonComponent
import com.projectlab.core.presentation.designsystem.component.ButtonVariant
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun BookingHotelFooter(
    modifier: Modifier = Modifier,
    price: Price
) {
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
        Text(
            text = "${price.currencyCode} ${price.amount} / per night",
            fontSize = 18.sp,
            fontWeight = FontWeight.W600,
            color = MaterialTheme.colorScheme.primary
        )


        ButtonComponent(
            text = "Book now!",
            onClick = { },
            variant = ButtonVariant.Primary,
        )
    }
}