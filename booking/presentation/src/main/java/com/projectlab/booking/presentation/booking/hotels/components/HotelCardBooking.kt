package com.projectlab.booking.presentation.booking.hotels.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun HotelCardBooking(
    modifier: Modifier = Modifier,
    hotel: Hotel
) {
    Row() {
        val painter = rememberAsyncImagePainter(
            model = hotel.displayImageUrl,
            placeholder = painterResource(R.drawable.ic_hotel),
            error = painterResource(R.drawable.ic_hotel),
            fallback = painterResource(R.drawable.ic_hotel)
        )
        Image(
            painter = painter,
            contentDescription = hotel.displayImageUrl,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(180.dp)
                .width(122.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        Column(
            modifier = Modifier
                .height(180.dp)
                .padding(vertical = 30.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                text = hotel.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                text = hotel.location.address,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                text = "${hotel.location.city}, ${hotel.location.country}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.width(160.dp)
            )
            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                text = hotel.phoneNumber,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.width(160.dp),
            )
        }
    }
}