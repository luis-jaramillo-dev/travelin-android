package com.projectlab.travelin_android.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.projectlab.auth.presentation.R
import com.projectlab.core.domain.model.Response
import com.projectlab.travelin_android.presentation.screens.login.components.LoginBottomBar
import com.projectlab.travelin_android.presentation.screens.login.components.LoginContent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onRegisterClick: () -> Unit,
    onLoggedIn: () -> Unit,
) {
    val loginFlow = viewModel.loginFlow.collectAsState()

    loginFlow.value.let {
        when (it) {
            // wasn't logged in
            null -> {
                LoginScreenScaffold(
                    email = viewModel.email,
                    emailErrorMessage = viewModel.emailError.value,
                    password = viewModel.password,
                    isFormValid = viewModel.isFormValid.value,
                    onLogin = {
                        viewModel.login()
                    },
                    onRegisterClick = onRegisterClick,
                )
            }

            Response.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            is Response.Failure -> {
                LoginScreenScaffold(
                    email = viewModel.email,
                    emailErrorMessage = viewModel.emailError.value,
                    password = viewModel.password,
                    isFormValid = viewModel.isFormValid.value,
                    onLogin = {
                        viewModel.login()
                    },
                    onRegisterClick = onRegisterClick,
                )

                Toast.makeText(
                    LocalContext.current,
                    stringResource(
                        R.string.failed_to_login,
                        it.exception?.message ?: stringResource(R.string.unknown_error),
                    ),
                    Toast.LENGTH_LONG,
                ).show()
            }

            is Response.Success -> {
                onLoggedIn()
                Toast.makeText(
                    LocalContext.current,
                    stringResource(R.string.logged_in),
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }
}

@Composable
fun LoginScreenScaffold(
    modifier: Modifier = Modifier,
    email: MutableState<String>,
    emailErrorMessage: String?,
    password: MutableState<String>,
    isFormValid: Boolean,
    onLogin: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Scaffold(
        bottomBar = { LoginBottomBar(onRegisterClick = onRegisterClick) },
    ) { paddingValues ->
        LoginContent(
            modifier = modifier.padding(paddingValues),
            email = email,
            emailErrorMessage = emailErrorMessage,
            password = password,
            isFormValid = isFormValid,
            onLogin = onLogin,
        )
    }
}
