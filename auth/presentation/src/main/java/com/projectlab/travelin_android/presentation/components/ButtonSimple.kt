package com.projectlab.travelin_android.presentation.components

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
import androidx.compose.ui.unit.dp

@Composable()
fun ButtonSimple(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
    contentColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onPrimary,
    isOutlined: Boolean = false
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .then(
                if (isOutlined)
                    Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(20.dp)
                    )
                else Modifier
                ),
        enabled = enabled,

        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) containerColor else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = contentColor
        ),

        shape = RoundedCornerShape(20.dp),

        onClick = {
            if (enabled) {
                onClick()
            }
        }
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}