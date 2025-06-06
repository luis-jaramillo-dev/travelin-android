package com.projectlab.booking.presentation.detail.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.projectlab.core.presentation.designsystem.component.BottomBookBar
import com.projectlab.core.presentation.designsystem.component.DescriptionBox
import com.projectlab.core.presentation.designsystem.component.GalleryDialog
import com.projectlab.core.presentation.designsystem.component.GallerySection
import com.projectlab.core.presentation.designsystem.component.TourCardHeader
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun ActivityDetailScreen(
    modifier: Modifier = Modifier,
    activityDetailViewModel: ActivityDetailViewModel,
    activityId: String,
    navController: NavController,
) {
    LaunchedEffect(Unit) {
        activityDetailViewModel.onViewDetail(activityId)
    }

    val uiState by activityDetailViewModel.uiState.collectAsState()

    val onFavoriteClick: () -> Unit = {
        activityDetailViewModel.updateFavorite()
    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        ActivityDetailScreenComponent(
            modifier = modifier,
            uiState = uiState,
            navController = navController,
            onFavoriteClick = onFavoriteClick,
        )
    }
}

@Composable
fun ActivityDetailScreenComponent(
    modifier: Modifier = Modifier,
    uiState: ActivityDetailUiState,
    navController: NavController,
    onFavoriteClick: () -> Unit,
) {
    val activity = uiState.activity
    val scrollState = rememberScrollState()
    var showGalleryDialog by remember { mutableStateOf(false) }

    activity?.let {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBookBar(activity = activity)
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .padding(bottom = MaterialTheme.spacing.ScreenVerticalSpacing),
                verticalArrangement = Arrangement.Top,
            ) {
                TourCardHeader(
                    modifier = Modifier,
                    activity = it,
                    navController = navController,
                    isFavorite = uiState.isFavorite,
                    isFavoriteLoading = uiState.isFavoriteLoading,
                    onFavoriteClick = onFavoriteClick,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.ScreenVerticalSpacing))

                if (activity.description.isNotEmpty()) {
                    DescriptionBox(
                        modifier = Modifier,
                        activity = it,
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.ScreenVerticalSpacing))

                if (activity.pictures.isNotEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        GallerySection(
                            modifier = modifier,
                            images = activity.pictures,
                            onSeeAllClick = { showGalleryDialog = true },
                        )
                    }
                }

                if (showGalleryDialog) {
                    GalleryDialog(
                        images = activity.pictures,
                        onDismissRequest = { showGalleryDialog = false },
                    )
                }
            }
        }
    }
}
