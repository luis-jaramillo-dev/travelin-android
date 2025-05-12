package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.presentation.designsystem.R

@Composable
fun TourListCard(modifier: Modifier = Modifier, activity: ActivityDto, city: String?) {
    val imageUrl = if (activity.pictures.isNotEmpty()) activity.pictures[0] else null
    val priceText = if (activity.price?.amount != null && activity.price.currencyCode != null) {
        "${activity.price.amount} ${activity.price.currencyCode}"
    } else {
        null
    }

    Row {
        if (imageUrl != null) {
            ImageCardWithFavorite(modifier = Modifier, image = imageUrl)
        } else {
            ImageCardWithFavorite(
                modifier = Modifier,
                image = painterResource(R.drawable.tourimageplaceholder).toString()
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        Column(
            modifier = Modifier.height(150.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            TourName(modifier = Modifier, activity.name)
            StarRatingBar(
                maxStars = 5,
                rating = activity.rating,
                textColor = Color.Black
            )
            TourCity(city)
            PriceText(priceText)
            if (activity.minimumDuration != ""){
                DurationText(modifier = Modifier, activity.minimumDuration)
            }

        }
    }
}

@Composable
fun TourName(modifier: Modifier = Modifier, name: String) {
    Text(
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        text = name,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.width(160.dp),
    )
}

@Composable
fun TourCity(city: String?) {
    if (city != null) {
        Text(
            fontSize = 10.sp,
            fontWeight = FontWeight.W400,
            text = city
        )
    }else{
        Text(
            fontSize = 10.sp,
            fontWeight = FontWeight.W400,
            text = stringResource(R.string.tour_list_card_city_not_available)
        )
    }
}

@Composable
fun PriceText(price: String?) {
    if (price == null || price == "Price not available") {
        Text(
            text = stringResource(R.string.tour_list_card_price_not_available),
            fontSize = 12.sp,
            fontWeight = FontWeight.W400
        )
    } else {
        val fromText = stringResource(R.string.tour_list_card_price_1, price)
        val personText = stringResource(R.string.tour_list_card_price_2)
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600
                    )
                ) {
                    append(fromText)
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W400
                    )
                ) {
                    append(personText)
                }
            }
        )
    }
}

@Composable
fun DurationText(modifier: Modifier = Modifier, duration: String) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0x1A000000)
            )
            .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
    ) {
        Text(
            text = duration,
            fontSize = 10.sp
        )
    }
}
