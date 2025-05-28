package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun BottomNavigationBar(
    current: String,
    onHomeClick: () -> Unit,
    ) {
    Surface(
        tonalElevation = MaterialTheme.spacing.TinySpacing,
        shadowElevation = MaterialTheme.spacing.SectionSpacing,
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.BottomBarHeight)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = Icons.Default.AccountCircle,
                label = "Profile",
                selected = current == "profile",
                onClick = { }
            )
            BottomNavItem(
                icon = Icons.Default.Favorite,
                label = "Trips",
                selected = current == "trips",
                onClick = { }
            )
            BottomNavItem(
                icon = Icons.Default.Home,
                label = "Home",
                selected = current == "home",
                onClick = onHomeClick
            )
        }
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = MaterialTheme.spacing.SmallSpacing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.TinySpacing))
        if (selected) {
            Box(
                modifier = Modifier
                    .width(MaterialTheme.spacing.ScreenHorizontalPadding)
                    .height(MaterialTheme.spacing.TinySpacing)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(MaterialTheme.spacing.CornerRadius)
                    )
            )
        } else {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.TinySpacing))
        }
    }
}
