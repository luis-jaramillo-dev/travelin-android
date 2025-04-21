package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.presentation.designsystem.R

@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Float,
    textColor: Color
) {
    val starSpacing = 4.dp

    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Default.Star
            val iconTintColor = if (isSelected) Color(0xFFFFB23F) else Color(0xFFDEDEDE)
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier.size(12.dp)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }

        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            color = textColor,
            text = stringResource(R.string.tour_list_card_reviews, 100)            )
    }
}

@Preview (showBackground = true)
@Composable
fun StarRatingBarPreview(){
    StarRatingBar(
        maxStars = 5,
        rating = 3F,
        textColor = Color.Black
    )
}