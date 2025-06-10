package com.projectlab.travelin_android.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.projectlab.auth.presentation.R
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.travelin_android.error.getErrorMessage
import com.projectlab.travelin_android.presentation.screens.login.components.LoginBottomBar
import com.projectlab.travelin_android.presentation.screens.login.components.LoginContent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onRegisterClick: () -> Unit,
    onLoggedIn: () -> Unit,
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
            onLoggedIn()

            Toast.makeText(
                LocalContext.current,
                stringResource(R.string.logged_in),
                Toast.LENGTH_SHORT,
            ).show()
        }

        // either not logged in, or an error while logging in
        else -> {
            LoginScreenScaffold(
                email = state.email,
                isEmailValid = state.isEmailValid,
                password = state.password,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onLogin = { viewModel.login() },
                onRegisterClick = onRegisterClick,
            )

            if (state.isError) {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(R.string.failed_to_login, getErrorMessage(state.error)),
                    Toast.LENGTH_LONG,
                ).show()
            }
        }
    }
}

@Composable
private fun LoginScreenScaffold(
    modifier: Modifier = Modifier,
    email: String,
    isEmailValid: Boolean,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Scaffold(
        bottomBar = { LoginBottomBar(onRegisterClick = onRegisterClick) },
    ) { paddingValues ->
        LoginContent(
            modifier = modifier.padding(paddingValues),
            email = email,
            isEmailValid = isEmailValid,
            password = password,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onLogin = onLogin,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenScaffoldPreview() {
    TravelinTheme {
        LoginScreenScaffold(
            email = "my@email.com",
            isEmailValid = false,
            password = "Password123",
            onEmailChange = {},
            onPasswordChange = {},
            onLogin = {},
            onRegisterClick = {},
        )
    }
}
