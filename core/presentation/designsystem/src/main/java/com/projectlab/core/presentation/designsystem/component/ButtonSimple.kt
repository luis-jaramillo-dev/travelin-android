package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable()
fun ButtonSimple(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    isOutlined: Boolean = false
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.ButtonHeight)
            .then(
                if (isOutlined)
                    Modifier.border(
                        width = MaterialTheme.spacing.Spacer,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(MaterialTheme.spacing.CornerRadius)
                    )
                else Modifier
            ),
        enabled = enabled,

        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) containerColor else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = contentColor
        ),

        shape = RoundedCornerShape(MaterialTheme.spacing.CornerRadius),

        onClick = {
            if (enabled) {
                onClick()
            }
        }
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}