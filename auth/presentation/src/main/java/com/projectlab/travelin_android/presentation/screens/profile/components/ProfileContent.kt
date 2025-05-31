package com.projectlab.travelin_android.presentation.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.projectlab.auth.presentation.R
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.travelin_android.presentation.screens.profile.ProfileState
import com.projectlab.travelin_android.presentation.screens.profile.ProfileViewModel

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel,
    onLogoutClick: () -> Unit,
    state: ProfileState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.BigSpacing))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.ScreenHorizontalPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.blank_profile_picture),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .size(MaterialTheme.spacing.ImageSize)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.SectionSpacing))

            Column {
                Text(
                    text = "${state.userEntityData.value.firstName} ${state.userEntityData.value.lastName}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Text(
                    text = state.userEntityData.value.email,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                Text(
                    text = "${state.userEntityData.value.countryCode} ${state.userEntityData.value.phoneNumber} ",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Text(
                    text = state.userEntityData.value.age,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SectionSpacing))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.Spacer)
                .padding(horizontal = MaterialTheme.spacing.ScreenHorizontalPadding)
                .background(MaterialTheme.colorScheme.surfaceDim)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.ScreenHorizontalPadding))
        ProfileSettings(
            viewModel = viewModel,
            onLogoutClick = {
                viewModel.logout()
                onLogoutClick()
            }
        )
    }
}