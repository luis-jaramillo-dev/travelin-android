package com.projectlab.core.presentation.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
object Spacing {
    /** 0.dp */
    val none: Dp = 0.dp

    /** 4.dp */
    val extraSmall: Dp = 4.dp

    /** 8.dp */
    val small: Dp = 8.dp

    /** 16.dp */
    val medium: Dp = 16.dp

    /** 24.dp */
    val semiLarge: Dp = 24.dp

    /** 32.dp */
    val large: Dp = 32.dp

    /** 40.dp */
    val extraLarge: Dp = 40.dp

    /** 48.dp */
    val extraLarge2: Dp = 48.dp

    /** 56.dp */
    val semiHuge: Dp = 56.dp

    /** 64.dp */
    val huge: Dp = 64.dp

    // Auth Layout spacing
    val ScreenHorizontalPadding: Dp = 30.dp
    val ScreenVerticalSpacing: Dp = 14.dp
    val SectionSpacing: Dp = 20.dp
    val ElementSpacing: Dp = 12.dp
    val BigSpacing: Dp = 70.dp
    val SmallSpacing: Dp = 8.dp
    val TinySpacing: Dp = 4.dp
    val Spacer: Dp = 1.dp

    // Auth Component sizing
    val ButtonHeight: Dp = 65.dp
    val BottomBarHeight: Dp = 69.dp
    val IconSize: Dp = 24.dp
    val SmallIconSize: Dp = 20.dp
    val ImageSize: Dp = 80.dp
    val LogoWidth: Dp = 137.dp
    val LogoHeight: Dp = 139.dp

    // Auth Container styles
    val CornerRadius: Dp = 20.dp
    val SmallCornerRadius: Dp = 12.dp
    val FieldHeight: Dp = 56.dp
}

val LocalSpacing = staticCompositionLocalOf { Spacing }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
