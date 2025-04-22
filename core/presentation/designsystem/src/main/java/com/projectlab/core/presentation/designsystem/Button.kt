package com.projectlab.core.presentation.designsystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBase(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconStart: ImageVector? = null,
    iconEnd: ImageVector? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    border: BorderStroke? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    fullWidth: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center
) {
    val lastModifier = if (fullWidth) {
        modifier
            .fillMaxWidth()
            .height(48.dp)
    } else {
        modifier.height(48.dp)
    }

    Button(
        onClick = onClick,
        modifier = lastModifier,
        enabled = enabled,
        shape = shape,
        border = border,
        colors = colors,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement
        ) {
            if (iconStart != null) {
                Icon(
                    imageVector = iconStart,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Text(text = text)
            if (iconEnd != null) {
                Icon(
                    imageVector = iconEnd,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


@Composable
fun ButtonPrimary(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconStart: ImageVector? = null,
    iconEnd: ImageVector? = null,
    fullWidth: Boolean = true
) {
    ButtonBase(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        iconStart = iconStart,
        iconEnd = iconEnd,
        fullWidth = fullWidth,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
        )
    )
}

@Composable
fun ButtonSecondary(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconStart: ImageVector? = null,
    iconEnd: ImageVector? = null,
    fullWidth: Boolean = true
) {
    ButtonBase(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        iconStart = iconStart,
        iconEnd = iconEnd,
        fullWidth = fullWidth,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f)
        )
    )
}

@Composable
fun ButtonTertiary(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconStart: ImageVector? = null,
    iconEnd: ImageVector? = null,
    fullWidth: Boolean = true
) {
    ButtonBase(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        iconStart = iconStart,
        iconEnd = iconEnd,
        fullWidth = fullWidth,
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.8f),

            )
    )
}

@Composable
fun ButtonOutline(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconStart: ImageVector? = null,
    iconEnd: ImageVector? = null,
    fullWidth: Boolean = true,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center
) {
    ButtonBase(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        iconStart = iconStart,
        iconEnd = iconEnd,
        fullWidth = fullWidth,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.outline,
            disabledContentColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        shape = RoundedCornerShape(6.dp)
    )
}

@Composable
fun ButtonIconTextArrow(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    fullWidth: Boolean = true
) {
    ButtonOutline(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        iconStart = icon,
        iconEnd = Icons.AutoMirrored.Filled.KeyboardArrowRight,
        fullWidth = fullWidth,
        horizontalArrangement = Arrangement.SpaceBetween
    )
}