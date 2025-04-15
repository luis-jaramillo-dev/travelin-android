package com.projectlab.onboarding.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getImageResIdByName(imageName: String): Int {
    val context = LocalContext.current
    return context.resources.getIdentifier(imageName, "drawable", context.packageName)
}