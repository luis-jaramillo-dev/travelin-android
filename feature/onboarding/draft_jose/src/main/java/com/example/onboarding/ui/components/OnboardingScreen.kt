package com.example.onboarding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.projectlab.feature.onboarding.draft_jose.R

@Composable
fun OnboardingScreen(
    backgroundImage: Painter,
    title: String,
    description: String,
    activePage: Int,
    onNextClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        val isDarkTheme = isSystemInDarkTheme()

        // TODO using placeholder for now
        val logo = if (isDarkTheme) {
            painterResource(R.drawable.logo_dark)
        } else {
            painterResource(R.drawable.logo_light)
        }

        Image(
            painter = backgroundImage,
            contentDescription = "Landscape image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = logo,
            contentDescription = "Logo",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 34.dp, top = 34.dp, bottom = 124.dp)
                .size(100.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.5f)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 25.dp, start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(
                        modifier = Modifier
                            .height(32.dp)
                            .padding(bottom = 25.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .height(32.dp)
                            .padding(bottom = 25.dp)
                    )
                    Button(
                        onNextClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Next",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .height(6.dp)
                            .width(20.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (index == activePage) Color.White.copy(alpha = 1f)
                                else Color.White.copy(alpha = 0.4f)
                            )
                    )
                }
            }
        }
    }
}
