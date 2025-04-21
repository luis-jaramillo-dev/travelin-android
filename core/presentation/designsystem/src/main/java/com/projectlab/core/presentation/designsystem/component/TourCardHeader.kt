package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.projectlab.core.data.mock.Activity
import com.projectlab.core.data.mock.MockActivities.sampleActivity
import com.projectlab.presentation.designsystem.R

@Composable
fun TourCardHeader(modifier: Modifier = Modifier, activity: Activity){
    Box(
        modifier = Modifier
            .height(428.dp)
    ) {
        AsyncImage(
            model = activity.pictures[0],
            contentDescription = "Header image",
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxHeight()
        )
        Column (
            modifier = Modifier
                .padding(24.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Image(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp)
                )
                Row {
                    Image(
                        painter = painterResource(R.drawable.share),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(5.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.unsaved_product),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(5.dp)
                    )

                }


            }

            Row {
                Column {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White,
                        text = sampleActivity.name,
                        modifier = modifier.width(200.dp),
                        maxLines = Int.MAX_VALUE
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    StarRatingBar(rating = 3F, textColor = Color.White)
                }
            }

        }


    }

}


@Preview
@Composable
fun TourCardHeaderPreview(){
    TourCardHeader(activity = sampleActivity)
}