package com.projectlab.booking.presentation.activities.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.projectlab.core.presentation.designsystem.component.TourCardHeader

@Composable
fun ActivityDetailScreen(
    modifier: Modifier = Modifier,
    activityDetailViewModel: ActivityDetailViewModel = hiltViewModel(),
) {
    val activity by activityDetailViewModel.activity.collectAsState()

    Column(
        modifier = modifier
    ){
        activity?.let {
            TourCardHeader(
                modifier = Modifier,
                activity = it
            )
        }
    }


}



@Composable
fun ActivityDetailScreenWithHilt(){

}