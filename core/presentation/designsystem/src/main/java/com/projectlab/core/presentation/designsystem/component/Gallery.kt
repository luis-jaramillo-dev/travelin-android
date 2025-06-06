package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun GallerySection(
    modifier: Modifier = Modifier,
    images: List<String>,
    onSeeAllClick: () -> Unit
) {
    Column (
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(327.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Row (modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    images.getOrNull(0)?.let {
                        GalleryImage(
                            url = it,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    images.getOrNull(1)?.let {
                        GalleryImage(
                            url = it,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(2.dp))
                images.getOrNull(2)?.let {
                    GalleryImage(
                        url = it,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onSeeAllClick,
            modifier = Modifier
                .width(156.dp)
                .height(42.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(5.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.White

            ),
            shape = RoundedCornerShape(4.dp)

        ) {
            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                text  = stringResource(R.string.gallery_see_all, images.size - 3))
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}

@Composable
fun GalleryDialog(
    modifier: Modifier = Modifier,
    images: List<String>,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton (onClick = onDismissRequest) {
                        IconBack(
                            modifier = modifier
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                ) {
                    items(images) { imageUrl ->
                        GalleryImage(
                            imageUrl,
                            Modifier
                                .fillMaxWidth()
                                .height(MaterialTheme.spacing.galleryImageSize)
                                .clip(RoundedCornerShape(8.dp)))

                    }
                }
            }
        }
    }
}

@Composable
fun GalleryImage(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(RoundedCornerShape(1.dp))
    )
}
