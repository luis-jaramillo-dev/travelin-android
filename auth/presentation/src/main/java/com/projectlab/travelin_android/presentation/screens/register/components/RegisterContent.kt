package com.projectlab.travelin_android.presentation.screens.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.projectlab.auth.presentation.R
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.travelin_android.presentation.components.ButtonSimple

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    countryCode: String,
    phoneNumber: String,
    age: String,
    email: String,
    password: String,
    acceptedTOS: Boolean,
    isPhoneNumberValid: Boolean,
    isAgeValid: Boolean,
    isEmailValid: Boolean,
    isPasswordValid: Boolean,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onCountryCodeChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onAcceptedTOSChange: (Boolean) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = MaterialTheme.spacing.small)
            .padding(horizontal = MaterialTheme.spacing.semiLarge)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        RegisterHeader(onLoginClick = onLoginClick)

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.ScreenVerticalSpacing))

        RegisterForm(
            firstName = firstName,
            lastName = lastName,
            countryCode = countryCode,
            phoneNumber = phoneNumber,
            age = age,
            email = email,
            password = password,
            acceptedTOS = acceptedTOS,
            isPhoneNumberValid = isPhoneNumberValid,
            isAgeValid = isAgeValid,
            isEmailValid = isEmailValid,
            isPasswordValid = isPasswordValid,
            onFirstNameChange = onFirstNameChange,
            onLastNameChange = onLastNameChange,
            onCountryCodeChange = onCountryCodeChange,
            onPhoneNumberChange = onPhoneNumberChange,
            onAgeChange = onAgeChange,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onAcceptedTOSChange = onAcceptedTOSChange,
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        ButtonSimple(
            text = stringResource(R.string.create_account_button),
            onClick = onRegisterClick,
            enabled = firstName.isNotEmpty()
                && lastName.isNotEmpty()
                && countryCode.isNotEmpty()
                && phoneNumber.isNotEmpty()
                && age.isNotEmpty()
                && email.isNotEmpty()
                && password.isNotEmpty()
                && acceptedTOS
                && isPhoneNumberValid
                && isAgeValid
                && isEmailValid
                && isPasswordValid,
        )
    }
}
