package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.core.presentation.designsystem.theme.customShapes
import com.projectlab.core.presentation.designsystem.theme.spacing

enum class ButtonVariant {
    Primary, Secondary, Tertiary, Outline
}

data class ButtonStyle(
    val colors: ButtonColors,
    val border: BorderStroke? = null,
    val shape: Shape = RoundedCornerShape(12.dp)
)

@Composable
private fun getButtonStyle(variant: ButtonVariant): ButtonStyle {
    return when (variant) {
        ButtonVariant.Primary -> ButtonStyle(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
            ),
            shape = MaterialTheme.customShapes.medium
        )

        ButtonVariant.Secondary -> ButtonStyle(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                disabledContentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f)
            ),
            shape = MaterialTheme.customShapes.medium
        )

        ButtonVariant.Tertiary -> ButtonStyle(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
                disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
                disabledContentColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.8f)
            ),
            shape = MaterialTheme.customShapes.small,
        )

        ButtonVariant.Outline -> ButtonStyle(
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.outline,
                disabledContentColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            shape = MaterialTheme.customShapes.small,
        )
    }
}

/**
 * A versatile and customizable button component styled according to the design system.
 *
 * This button supports multiple visual variants (primary, secondary, tertiary, outline),
 * optional leading and trailing icons, full-width behavior, and layout customization.
 *
 * The component internally resolves style (colors, shapes, borders) using the [ButtonVariant] enum.
 *
 * ## Usage
 *
 * Basic primary button:
 * ```
 * ButtonComponent(
 *     text = "Continue",
 *     onClick = { /* handle click */ },
 *     variant = ButtonVariant.Primary
 * )
 * ```
 *
 * With leading icon and full width:
 * ```
 * ButtonComponent(
 *     text = "Save",
 *     onClick = { /* handle save */ },
 *     iconStart = { Icon(Icons.Default.Save, contentDescription = null) },
 *     fullWidth = true,
 *     variant = ButtonVariant.Secondary
 * )
 * ```
 *
 * With both icons and spaced content:
 * ```
 * ButtonComponent(
 *     text = "Details",
 *     onClick = { /* handle click */ },
 *     iconStart = { Icon(Icons.Default.Info, contentDescription = null) },
 *     iconEnd = { Icon(Icons.Default.ArrowForward, contentDescription = null) },
 *     horizontalArrangement = Arrangement.SpaceBetween,
 *     variant = ButtonVariant.Outline
 * )
 * ```
 *
 * @param text The text to display inside the button.
 * @param onClick The callback triggered when the button is clicked.
 * @param variant The visual style of the button (Primary, Secondary, Tertiary, Outline).
 * @param modifier The modifier to be applied to the button.
 * @param enabled Whether the button is enabled. Defaults to true.
 * @param iconStart An optional composable icon shown before the text.
 * @param iconEnd An optional composable icon shown after the text.
 * @param fullWidth If true, the button expands to fill the available width.
 * @param horizontalArrangement How content is arranged horizontally inside the button.
 */
@Composable
fun ButtonComponent(
    text: String,
    onClick: () -> Unit,
    variant: ButtonVariant,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconStart: (@Composable () -> Unit)? = null,
    iconEnd: (@Composable () -> Unit)? = null,
    fullWidth: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center
) {
    val style = getButtonStyle(variant)
    Button(
        onClick = onClick,
        modifier = modifier
            .then(if (fullWidth == true) Modifier.fillMaxWidth(1f) else Modifier.wrapContentWidth())
            .height(MaterialTheme.spacing.semiHuge),
        enabled = enabled,
        shape = style.shape,
        border = style.border,
        colors = style.colors,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement,
            modifier = Modifier.wrapContentWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            ) {
                if (iconStart != null) iconStart()
                Text(text = text, style = MaterialTheme.typography.bodyLarge)
            }
            if (iconEnd != null) iconEnd()
        }
    }
}

/**
 * A specialized button variant displaying a leading icon and a trailing arrow.
 *
 * Commonly used for navigational actions (e.g., "Next", "Details", etc.).
 *
 * @param text The button label.
 * @param onClick Action to perform when clicked.
 * @param modifier Modifier for layout customization.
 * @param enabled Whether the button is enabled.
 * @param icon Leading icon (before the text).
 * @param fullWidth If true, the button fills its container width.
 */
@Composable
fun ButtonIconTextArrow(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    fullWidth: Boolean = false
) {
    ButtonComponent(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        iconStart = icon,
        iconEnd = { IconForward(modifier = Modifier) },
        fullWidth = fullWidth,
        horizontalArrangement = Arrangement.SpaceBetween,
        variant = ButtonVariant.Outline
    )
}

@Preview(showBackground = true)
@Composable
fun ButtonShowcasePreview() {
    TravelinTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {
            ButtonComponent(
                text = "Primary",
                onClick = {},
                variant = ButtonVariant.Primary
            )

            ButtonComponent(
                text = "Secondary",
                onClick = {},
                variant = ButtonVariant.Secondary
            )

            ButtonComponent(
                text = "Tertiary",
                onClick = {},
                variant = ButtonVariant.Tertiary
            )

            ButtonComponent(
                text = "Outline",
                onClick = {},
                variant = ButtonVariant.Outline
            )

            ButtonIconTextArrow(
                text = "Navigate",
                onClick = {},
                icon = { IconBus(modifier = Modifier) }
            )
        }
    }
}
