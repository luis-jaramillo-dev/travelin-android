package com.projectlab.travelin_android.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable()
fun ButtonSimple(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    isOutlined: Boolean = false,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.FieldHeight)
            .then(
                if (isOutlined)
                    Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.large,
                    )
                else Modifier
            ),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) {
                containerColor
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            contentColor = contentColor,
        ),
        shape = MaterialTheme.shapes.large,
        onClick = {
            if (enabled) {
                onClick()
            }
        }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium,
            ),
        )
    }
}
