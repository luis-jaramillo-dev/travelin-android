package com.projectlab.booking.presentation.search.hotels.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.projectlab.booking.models.HotelUi
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun SearchHotelListItem(
    hotelUi: HotelUi,
    onClickDetail: () -> Unit,
    onClickFavorite: () -> Unit,
    onClickUnfavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(modifier = modifier.clickable(onClick = onClickDetail)) {
        val painter = rememberAsyncImagePainter(
            model = hotelUi.displayImageUrl,
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
                contentDescription = hotelUi.name,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )
            if (hotelUi.isFavorite) {
                Image(
                    painter = painterResource(R.drawable.ic_favorite_heart),
                    contentDescription = stringResource(R.string.unfavorite_hotel, hotelUi.name),
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.TopEnd)
                        .padding(5.dp)
                        .clickable {
                            if (hotelUi.isFavorite) {
                                onClickFavorite()
                            } else {
                                onClickUnfavorite()
                            }
                        }
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.ic_unfavorite_heart),
                    contentDescription = stringResource(R.string.favorite_hotel, hotelUi.name),
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.TopEnd)
                        .padding(5.dp)
                        .clickable {
                            if (hotelUi.isFavorite) {
                                onClickFavorite()
                            } else {
                                onClickUnfavorite()
                            }
                        }
                )
            }
        }

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

        Column(
            modifier = Modifier
                .height(122.dp)
                .padding(vertical = MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            Column {
                Text(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    text = hotelUi.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.width(160.dp),
                )

                Row(
                    modifier = Modifier.selectableGroup(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..5) {
                        val isSelected = i <= hotelUi.stars
                        val icon = if (isSelected) Icons.Filled.Star else Icons.Default.Star
                        val iconTintColor = if (isSelected) Color(0xFFFFB23F) else Color(0xFFDEDEDE)
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = iconTintColor,
                            modifier = Modifier.size(12.dp)
                        )

                        if (i < 5) {
                            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                text = stringResource(R.string.price_per_night, hotelUi.price.formatted),
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.width(160.dp),
            )
        }
    }
}