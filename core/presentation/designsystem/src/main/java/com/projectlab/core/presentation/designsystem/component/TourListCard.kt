package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectlab.core.data.mock.Activity
import com.projectlab.core.data.mock.MockActivities
import com.projectlab.core.presentation.designsystem.R





@Composable
fun TourListCard (modifier: Modifier = Modifier, activity: Activity){
    val city = "Krong Siem Reap"
    Row {
        ImageCardWithFavorite(modifier = Modifier, image =  activity.pictures[0])
        Spacer(modifier = Modifier.width(15.dp))
        Column (modifier = Modifier.height(134.dp), verticalArrangement = Arrangement.SpaceAround){
            TourName(modifier = Modifier, activity.name)
            StarRatingBar(
                maxStars = 5,
                // Rating comes from API
                rating = activity.rating,
                textColor = Color.Black
            )
            TourCity(city)
            PriceText(activity.price.amount)
            DurationText(modifier = Modifier, activity.minimumDuration)

        }
    }
}



//Tour name comes from API
@Composable
fun TourName (modifier: Modifier = Modifier, name: String){
    Text(
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        text = name,
        modifier = modifier.width(160.dp),
        maxLines = Int.MAX_VALUE
    )
}

@Composable
fun TourCity (city: String){
    Text(
        fontSize = 10.sp,
        fontWeight = FontWeight.W400,
        text = city)
}

//Price comes from API
@Composable
fun PriceText (price: String){
    val fromText = stringResource(R.string.tour_list_card_price_1, price)
    val personText = stringResource(R.string.tour_list_card_price_2)
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600
                )
            )
            {
                append(fromText)
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W400
                )
            )
            {
                append(personText)
            }
        }

    )
}

@Composable
fun DurationText (modifier: Modifier = Modifier, duration: String){
    Box(
        modifier = modifier
            .border(
            width = 1.dp,
            color = Color(0x1A000000)
            ).padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
    ){
        Text(
            text = duration,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TourListCardPreview(){
    TourListCard(activity = MockActivities.sampleActivity)
}