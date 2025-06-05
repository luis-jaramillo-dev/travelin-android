package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.vector.ImageVector
import com.projectlab.core.presentation.designsystem.theme.spacing

enum class BottomNavRoute {
    HOME,
    FAVORITES,
    TRIPS,
    PROFILE,
}

@Composable
fun BottomNavigationBar(
    current: BottomNavRoute,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onTripsClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    Surface(
        tonalElevation = MaterialTheme.spacing.TinySpacing,
        shadowElevation = MaterialTheme.spacing.SectionSpacing,
        color = Color.Transparent,
        modifier = Modifier
            .navigationBarsPadding()
            .drawWithContent {
                // clip left, right, and bottom
                clipRect(
                    left = 0f,
                    top = -Float.MAX_VALUE,
                    right = size.width,
                    bottom = size.height,
                ) {
                    this@drawWithContent.drawContent()
                }
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.BottomBarHeight)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BottomNavItem(
                icon = Icons.Default.Home,
                label = "Home",
                selected = current == BottomNavRoute.HOME,
                onClick = onHomeClick,
            )
            BottomNavItem(
                icon = Icons.Default.Favorite,
                label = "Favorites",
                selected = current == BottomNavRoute.FAVORITES,
                onClick = onFavoritesClick,
            )
            BottomNavItem(
                icon = Icons.Default.Flight,
                label = "Trips",
                selected = current == BottomNavRoute.TRIPS,
                onClick = onTripsClick,
            )
            BottomNavItem(
                icon = Icons.Default.AccountCircle,
                label = "Profile",
                selected = current == BottomNavRoute.PROFILE,
                onClick = onProfileClick,
            )
        }
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = MaterialTheme.spacing.SmallSpacing),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
            ),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.TinySpacing))
        if (selected) {
            Box(
                modifier = Modifier
                    .width(MaterialTheme.spacing.ScreenHorizontalPadding)
                    .height(MaterialTheme.spacing.TinySpacing)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(MaterialTheme.spacing.CornerRadius),
                    ),
            )
        } else {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.TinySpacing))
        }
    }
}
