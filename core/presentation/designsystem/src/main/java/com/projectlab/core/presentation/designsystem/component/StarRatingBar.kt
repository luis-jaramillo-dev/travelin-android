package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.gold
import com.projectlab.core.presentation.designsystem.theme.spacing
import kotlin.math.ceil

@Composable
fun StarRatingBar(
    rating: String,
    modifier: Modifier = Modifier,
    starSize: Dp = MaterialTheme.spacing.ElementSpacing,
    spaceBetween: Dp = MaterialTheme.spacing.extraSmall,
    showRatingText: Boolean = true,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    maxStars: Int = 5,
) {
    val star = painterResource(R.drawable.star_24px)
    val starHalf = painterResource(R.drawable.star_half_24px)
    val starFull = painterResource(R.drawable.star_full_24px)
    val ratingFloat = rating.toFloatOrNull() ?: 0f

    Row(
        modifier = modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 1..maxStars) {
            val icon = when {
                i <= ratingFloat -> starFull
                i == ceil(ratingFloat).toInt() -> starHalf
                else -> star
            }

            Icon(
                painter = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.gold,
                modifier = Modifier.size(starSize)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(spaceBetween))
            }
        }

        if (showRatingText) {
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

            Text(
                text = if (ratingFloat > 0) {
                    rating.toString()
                } else {
                    stringResource(R.string.tour_list_card_no_reviews)
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    color = textColor,
                ),
            )
        }
    }
}
