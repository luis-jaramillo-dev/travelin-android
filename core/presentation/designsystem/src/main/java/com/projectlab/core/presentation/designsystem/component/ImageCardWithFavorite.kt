package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.projectlab.presentation.designsystem.R

@Composable
fun ImageCardWithFavorite (modifier: Modifier = Modifier){

    var isSaved by remember { mutableStateOf(false) }

    val icon = if (isSaved) {
        painterResource(R.drawable.saved_product)
    } else {
        painterResource(R.drawable.unsaved_product)
    }

    Box(
        modifier = Modifier
            .size(122.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        Image(
            painter = painterResource(R.drawable.tourimageplaceholder),
            contentDescription = "Tour image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = icon,
            contentDescription = "Save product",
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.TopEnd)
                .padding(5.dp)
                .clickable { isSaved = !isSaved }
        )
    }

}