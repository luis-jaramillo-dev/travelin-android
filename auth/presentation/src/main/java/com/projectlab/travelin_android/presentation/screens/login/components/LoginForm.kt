package com.projectlab.travelin_android.presentation.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.projectlab.auth.presentation.R
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.travelin_android.presentation.components.ButtonSimple
import com.projectlab.travelin_android.presentation.components.OutlinedTextFieldPassword
import com.projectlab.travelin_android.presentation.components.OutlinedTextFieldSimple

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    email: MutableState<String>,
    emailErrorMessage: String?,
    password: MutableState<String>,
    isFormValid: Boolean,
    onLogin: () -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = 30.dp)
    ) {
        OutlinedTextFieldSimple(
            label = stringResource(R.string.email),
            placeholderText = stringResource(R.string.enter_your_email_address),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            value = email,
            isError = emailErrorMessage != null,
            errorMessage = emailErrorMessage,
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.ScreenVerticalSpacing))

        OutlinedTextFieldPassword(
            label = stringResource(R.string.password),
            placeholderText = stringResource(R.string.enter_your_password),
            value = password,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            // do not validate password on login
            isError = false,
            errorMessage = null,
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.regular))

        ButtonSimple(
            text = stringResource(R.string.login),
            onClick = { onLogin() },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid,
        )
    }
}
