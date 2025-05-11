package com.projectlab.core.presentation.designsystem.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Defines standardized shapes used across the UI.
 */
@Immutable
object CustomShapes {
    /** No rounded corners (0.dp) */
    val none: RoundedCornerShape = RoundedCornerShape(0.dp)

    /** Slightly rounded corners (4.dp) */
    val extraSmall: RoundedCornerShape = RoundedCornerShape(4.dp)

    /** Moderately rounded corners (8.dp) */
    val small: RoundedCornerShape = RoundedCornerShape(8.dp)

    /** Commonly used rounded corners (12.dp) */
    val medium: RoundedCornerShape = RoundedCornerShape(12.dp)

    /** Prominent rounded corners (16.dp) */
    val large: RoundedCornerShape = RoundedCornerShape(16.dp)

    /** Strongly rounded corners (24.dp) */
    val extraLarge: RoundedCornerShape = RoundedCornerShape(24.dp)

    /** Completely circular shape */
    val circle: RoundedCornerShape = CircleShape
}

/**
 * Provides custom shapes throughout the Compose hierarchy.
 */
val LocalCustomShapes = staticCompositionLocalOf { CustomShapes }

/**
 * Easy access to custom shapes through MaterialTheme.
 */
val androidx.compose.material3.MaterialTheme.customShapes: CustomShapes
    @androidx.compose.runtime.Composable
    @androidx.compose.runtime.ReadOnlyComposable
    get() = LocalCustomShapes.current
