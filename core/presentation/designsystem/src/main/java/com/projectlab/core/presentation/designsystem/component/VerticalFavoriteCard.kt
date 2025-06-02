package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.customShapes
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun VerticalFavoriteCard(
    name: String,
    description: String,
    location: String,
    rating: Float,
    pictureUrl: String?,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageHeight: Dp = 200.dp,
    favoriteButtonSize: Dp = 40.dp,
    favoriteButtonPadding: Dp = 16.dp,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val placeholder = painterResource(R.drawable.tourimageplaceholder)

    val painter = rememberAsyncImagePainter(
        model = pictureUrl ?: placeholder.toString(),
        placeholder = placeholder,
        error = placeholder,
        fallback = placeholder,
    )

    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1f),
                shape = MaterialTheme.customShapes.large,
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    MaterialTheme.customShapes.large.copy(
                        bottomStart = CornerSize(0.dp),
                        bottomEnd = CornerSize(0.dp),
                    )
                )
                .height(imageHeight),
        ) {
            Image(
                painter = painter,
                contentDescription = stringResource(R.string.image_of, name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                    ) {
                        onClick()
                    },
            )

            Image(
                painter = painterResource(R.drawable.saved_product),
                contentDescription = stringResource(R.string.remove_favorite),
                modifier = Modifier
                    .size(favoriteButtonSize + favoriteButtonPadding * 2)
                    .align(Alignment.TopEnd)
                    .padding(favoriteButtonPadding)
                    .clickable {
                        onFavoriteClick()
                    },
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = MaterialTheme.spacing.regular,
                    vertical = MaterialTheme.spacing.extraSmall,
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                ) {
                    onClick()
                },
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.tiny))

            Text(
                text = location,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.tiny))

            StarRatingBar(
                rating = rating,
                spaceBetween = 0.dp,
                showRatingText = false,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.regular))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
