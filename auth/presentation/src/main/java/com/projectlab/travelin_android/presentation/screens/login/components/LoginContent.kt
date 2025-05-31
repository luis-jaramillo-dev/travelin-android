package com.projectlab.travelin_android.presentation.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.projectlab.travelin_android.presentation.components.ButtonSimple
import com.projectlab.travelin_android.presentation.components.OutlinedTextFieldPassword
import com.projectlab.travelin_android.presentation.components.OutlinedTextFieldSimple
import com.projectlab.travelin_android.presentation.screens.login.LoginState
import com.projectlab.travelin_android.presentation.screens.login.LoginViewModel

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: LoginViewModel,
    state: LoginState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        LoginHeader()
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {
            OutlinedTextFieldSimple(
                label = "Email",
                placeholderText = "Enter your email address",
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                value = state.email,
                isError = viewModel.emailError.value != null,
                errorMessage = viewModel.emailError.value
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextFieldPassword(
                label = "Password",
                placeholderText = "Enter your password",
                value = state.password,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                isError = viewModel.passwordError.value != null,
                errorMessage = viewModel.passwordError.value,
            )
            Spacer(modifier = Modifier.height(4.dp))
            ButtonSimple(
                text = "Login",
                onClick = { viewModel.login() },
                modifier = Modifier.fillMaxWidth(),
                enabled = viewModel.isFormValid.value
            )
        }
    }
}