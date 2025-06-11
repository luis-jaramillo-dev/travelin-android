package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun TourCardHeader(
    modifier: Modifier = Modifier,
    activity: ActivityDto,
    navController: NavController,
    isFavorite: Boolean,
    isFavoriteLoading: Boolean,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    val favoriteIcon = if (isFavorite) {
        painterResource(R.drawable.saved_product)
    } else {
        painterResource(R.drawable.unsaved_product)
    }

    Box(
        modifier = Modifier
            .height(MaterialTheme.spacing.TourCardHeaderSize),
    ) {
        if (activity.pictures.isNotEmpty()) {
            AsyncImage(
                model = activity.pictures[0],
                contentDescription = "Header image",
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxHeight(),
            )
        } else {
            Image(
                painter = painterResource(R.drawable.tourimageplaceholder),
                contentDescription = "Header image",
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxHeight(),
            )
        }

        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                BackArrowIconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp),
                )

                Row {
                    Image(
                        painter = painterResource(R.drawable.share),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(5.dp),
                    )
                    if (isFavoriteLoading){
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(5.dp),
                        )
                    }else {

                        Image(
                            painter = favoriteIcon,
                            contentDescription = "Favorite toggle",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(5.dp)
                                .clickable {
                                    onFavoriteClick()
                                },
                        )
                    }

                }
            }

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                    .background(Color(0xAA1A1A1A))
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium),
            ) {
                Column {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White,
                        text = activity.name,
                        modifier = modifier.width(200.dp),
                        maxLines = Int.MAX_VALUE,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    StarRatingBar(rating = activity.rating, textColor = Color.White)
                }
            }
        }
    }
}
