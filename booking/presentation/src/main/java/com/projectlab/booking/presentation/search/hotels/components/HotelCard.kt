package com.projectlab.booking.presentation.search.hotels.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.projectlab.booking.presentation.screens.HotelsViewModel
import com.projectlab.booking.presentation.screens.hotels.search.components.PriceText
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.component.StarRatingBar
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun HotelCard(
    modifier: Modifier = Modifier,
    hotel: Hotel,
    viewModel: HotelsViewModel,
    onPress: () -> Unit
) {

    var isFavorite by remember { mutableStateOf(hotel.isFavourite) }

    Row(modifier = modifier.clickable(onClick = { onPress() })) {


        val icon = if (isFavorite) {
            painterResource(R.drawable.saved_product)
        } else {
            painterResource(R.drawable.unsaved_product)
        }

        val painter = rememberAsyncImagePainter(
            model = hotel.displayImageUrl,
            placeholder = painterResource(R.drawable.ic_hotel),
            error = painterResource(R.drawable.ic_hotel),
            fallback = painterResource(R.drawable.ic_hotel)
        )

        Box(
            modifier = Modifier
                .size(122.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {
            Image(
                painter = painter,
                contentDescription = "Hotel image",
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )

            Image(
                painter = icon,
                contentDescription = "Save hotel",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.TopEnd)
                    .padding(5.dp)
                    .clickable {
                        if (isFavorite) {
                            viewModel.unfavoriteHotel(hotelId = hotel.id)
                        } else {
                            viewModel.favoriteHotel(hotelId = hotel.id)
                        }
                        isFavorite = !isFavorite

                    }
            )
        }

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

        Column(
            modifier = Modifier.height(150.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                text = hotel.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.width(160.dp),
            )
            StarRatingBar(
                maxStars = 5,
                rating = hotel.rating.stars?.toFloat() ?: 0f,
                textColor = Color.Black
            )

            PriceText(hotel.displayPrice.toString())


        }
    }
}
