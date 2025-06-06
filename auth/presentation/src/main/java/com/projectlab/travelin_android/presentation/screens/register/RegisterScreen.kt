package com.projectlab.travelin_android.presentation.screens.register

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.projectlab.travelin_android.presentation.screens.register.components.RegisterBottomBar
import com.projectlab.travelin_android.presentation.screens.register.components.RegisterContent

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onLoginClick: () -> Unit,
    onSuccessfulClick: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    when {
        state.loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        state.success -> {
            viewModel.createUser()

            LaunchedEffect(Unit) {
                onSuccessfulClick()
            }

            Toast.makeText(
                LocalContext.current, "User logged ",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {
            RegisterScreenScaffold(
                firstName = state.firstName,
                lastName = state.lastName,
                countryCode = state.countryCode,
                phoneNumber = state.phoneNumber,
                age = state.age,
                email = state.email,
                password = state.password,
                acceptedTOS = state.acceptedTOS,
                isAgeValid = state.isAgeValid,
                isEmailValid = state.isEmailValid,
                isPasswordValid = state.isPasswordValid,
                onFirstNameChange = viewModel::onFirstNameChange,
                onLastNameChange = viewModel::onLastNameChange,
                onCountryCodeChange = viewModel::onCountryCodeChange,
                onPhoneNumberChange = viewModel::onPhoneNumberChange,
                onAgeChange = viewModel::onAgeChange,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onAcceptedTOSChange = viewModel::onAcceptedTOSChange,
                onLoginClick = onLoginClick,
                onRegisterClick = { viewModel.register() },
            )

            if (state.isError) {
                Toast.makeText(
                    LocalContext.current, "User fail ${state.error} ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

@Composable
private fun RegisterScreenScaffold(
    firstName: String,
    lastName: String,
    countryCode: String,
    phoneNumber: String,
    age: String,
    email: String,
    password: String,
    acceptedTOS: Boolean,
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
    Scaffold(
        bottomBar = { RegisterBottomBar(onLoginClick = onLoginClick) },
    ) { paddingValues ->
        RegisterContent(
            modifier = Modifier.padding(paddingValues),
            firstName = firstName,
            lastName = lastName,
            countryCode = countryCode,
            phoneNumber = phoneNumber,
            age = age,
            email = email,
            password = password,
            acceptedTOS = acceptedTOS,
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
            onLoginClick = onLoginClick,
            onRegisterClick = onRegisterClick,
        )
    }
}
