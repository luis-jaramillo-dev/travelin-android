package com.projectlab.travelin_android.presentation.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    email: String,
    isEmailValid: Boolean,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.BigSpacing))

        LoginHeader()

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        LoginForm(
            email = email,
            isEmailValid = isEmailValid,
            password = password,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onLogin = onLogin,
        )
    }
}
