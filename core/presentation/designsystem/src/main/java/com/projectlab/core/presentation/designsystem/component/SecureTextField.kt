package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SecureTextField(
    modifier: Modifier = Modifier,
    border: Int = 1,
    alpha: Float = 0.1f,
    borderColor : Color = MaterialTheme.colorScheme.scrim.copy(alpha = alpha),
    state: TextFieldState,
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    textColor: Color = MaterialTheme.colorScheme.scrim,
    iconColor: Color = MaterialTheme.colorScheme.scrim
    ){
    var showPassword: Boolean by remember { mutableStateOf(false) }
    BasicSecureTextField(
        state = state,
        textObfuscationMode =
            if (showPassword) {
                TextObfuscationMode.Visible
            } else {
                TextObfuscationMode.RevealLastTyped
            },
        textStyle = MaterialTheme.typography.titleLarge.copy(color = textColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = marginStart.dp,
                end = marginEnd.dp,
                top = marginTop.dp,
                bottom = marginBottom.dp
            )
            .border(border.dp, borderColor, RoundedCornerShape(15.dp),)
            .height(51.dp)
            .padding(6.dp),
        decorator = { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp, end = 48.dp)
                ) {
                    innerTextField()
                }
                IconButton(
                    onClick = { showPassword = !showPassword },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    if (!showPassword) {
                        IconEye(modifier = modifier, tint = iconColor)
                    } else {
                        IconVisibilityOff(modifier = modifier, tint = iconColor)
                    }
                }
            }
        }
    )
}

@Composable
fun TravelinSecureTextField(
    modifier: Modifier = Modifier,
    title: String? = null,
    state: TextFieldState,
    error: String? = null,
    border: Int = 1,
    alpha: Float = 0.1f,
    borderColor : Color = MaterialTheme.colorScheme.scrim.copy(alpha = alpha),
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    height: Int = 84,
    textColor: Color = MaterialTheme.colorScheme.scrim,
    iconColor: Color = MaterialTheme.colorScheme.scrim
){
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(
                top = marginTop.dp,
                bottom = marginBottom.dp,
                start = marginStart.dp,
                end = marginEnd.dp
            )
            .height(height.dp)
    ) {
        if (title != null) {
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                color = textColor,
                modifier = modifier.padding(bottom = 2.dp)
            )
        }
        SecureTextField(
            modifier = modifier,
            border = border,
            alpha = alpha,
            borderColor = (
                    if (error == null) {
                        borderColor
                    } else {
                        MaterialTheme.colorScheme.error
                    }),
            state = state,
            textColor = textColor,
            iconColor = iconColor
        )
        if (error != null) {
            Text(
                text = error,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                color = MaterialTheme.colorScheme.error,
                modifier = modifier.padding(top = 0.dp)
            )
        }
    }
}