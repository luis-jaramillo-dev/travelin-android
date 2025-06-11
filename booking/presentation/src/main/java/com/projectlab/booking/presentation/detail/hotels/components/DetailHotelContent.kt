package com.projectlab.booking.presentation.screens.hotels.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.projectlab.booking.models.HotelUi
import com.projectlab.core.domain.model.HotelOffer
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun DetailHotelContent(
    modifier: Modifier = Modifier,
    hotelUi: HotelUi
) {
    val scrollState = rememberScrollState()
    var imageHeight by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .padding(
                bottom =
                    MaterialTheme.spacing.ScreenVerticalSpacing
            ),
        contentAlignment = Alignment.TopStart
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .weight(1f)
            ) {
                ImageSlider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged { imageHeight = it.height },
                    images = hotelUi.photoUrls
                )

                NameAndAddress(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 24.dp),
                    name = hotelUi.name,
                    address = hotelUi.address
                )

                if (hotelUi.hotelOffers != emptyList<HotelOffer>()) {
                    Details(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp)
                            .padding(horizontal = 24.dp),
                        hotelOffer = hotelUi.hotelOffers!![0]

                    )
                }

                Description(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .padding(horizontal = 24.dp),
                    text = hotelUi.hotelOffers?.get(0)!!.room.description
                )

                Amenities(
                    amenities = hotelUi.amenities!!,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .padding(horizontal = 24.dp),
                )
            }
        }
    }
}

@Composable
private fun Amenities(
    modifier: Modifier = Modifier,
    column: Int = 4,
    amenities: List<String>
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.amenities),
        )
        amenities.splitByRow().forEach { facilities ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                amenities?.forEach { amenity ->
                    LabeledIcon(
                        modifier = Modifier.weight(1f),
                        iconRes = getAmenityIcon(amenity),
                        text = amenity
                    )
                }
                val emptyColumns = column - amenities.size
                repeat(emptyColumns) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

private fun List<String>.splitByRow(columns: Int = 4): List<List<String>> {
    var mutableFacilities = this
    val result = mutableListOf<List<String>>()

    while (mutableFacilities.isNotEmpty()) {
        val rowItems = mutableFacilities.take(columns)
        mutableFacilities = mutableFacilities.drop(columns)
        result.add(rowItems)
    }

    return result.toList()
}


fun getAmenityIcon(amenity: String): Int {
    return when (amenity) {
        "SWIMMING_POOL" -> R.drawable.ic_swimming_pool
        "WI_FI" -> R.drawable.ic_wi_fi
        "RESTAURANT" -> R.drawable.ic_restaurant
        "PARKING" -> R.drawable.ic_parking
        "MEETING_ROOM" -> R.drawable.ic_meeting_room
        "ELEVATOR" -> R.drawable.ic_elevator
        "FITNESS_CENTER" -> R.drawable.ic_fitness_center
        "DAY_AND_NIGHT_OPEN" -> R.drawable.ic_24_7
        else -> R.drawable.ic_elevator
    }
}

@Composable
private fun Description(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.description),
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
        )
        Text(
            text = text,
        )
    }
}


@Composable
private fun Details(
    hotelOffer: HotelOffer,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Details",
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LabeledIcon(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_hotel,
                text = stringResource(R.string.hotel),
            )
            LabeledIcon(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_bedroom,
                text = hotelOffer.room.beds.toString()
            )

            LabeledIcon(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_bathroom,
                text = hotelOffer.room.bathrooms.toString()
            )

            LabeledIcon(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_square,
                text = stringResource(R.string.sqm,hotelOffer.room.squareMeters ),
             )
        }
    }
}

@Composable
private fun LabeledIcon(
    @DrawableRes iconRes: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    val brush = Brush.linearGradient(
        listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.primary
        )
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush = brush, blendMode = BlendMode.SrcAtop)
                    }
                },
            painter = painterResource(iconRes),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "",
        )
        Text(
            text = text,
            color = Color(0xFF424242),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NameAndAddress(
    name: String,
    address: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = "",
            )
            Text(
                text = address,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImageSlider(
    images: List<String>,
    modifier: Modifier
) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    Box(modifier = modifier.height(250.dp)) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { page ->
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = images[page],
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }

        SliderIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            slides = images,
            currentSlide = images[pagerState.currentPage]
        )
    }
}

@Composable
fun <T> SliderIndicator(
    slides: List<T>,
    currentSlide: T,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        slides.forEach { slide ->
            val active = slide == currentSlide
            IndicatorDot(
                modifier = Modifier.padding(horizontal = 8.dp),
                active = active
            )
        }
    }
}

@Composable
private fun IndicatorDot(
    active: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(8.dp)
            .clip(CircleShape)
            .then(
                if (active) {
                    Modifier.background(
                        Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.inversePrimary,
                                MaterialTheme.colorScheme.primary
                            )
                        )
                    )
                } else {
                    Modifier.background(
                        Color.White
                    )
                }
            )
    )
}
