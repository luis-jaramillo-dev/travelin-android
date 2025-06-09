package com.projectlab.travelin_android.presentation.screens.register

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
import com.projectlab.auth.presentation.R
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
            onSuccessfulClick()

            Toast.makeText(
                LocalContext.current,
                stringResource(R.string.logged_in),
                Toast.LENGTH_SHORT,
            ).show()
        }

        else -> {
            RegisterScreenScaffold(
                formState = state.formState,
                onFormAction = viewModel::onFormAction,
                onLoginClick = onLoginClick,
                onRegisterClick = { viewModel.register() },
            )

            if (state.isError) {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(
                        R.string.failed_to_register,
                        state.error ?: stringResource(R.string.unknown_error),
                    ),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

@Composable
private fun RegisterScreenScaffold(
    formState: FormState,
    onFormAction: (FormAction) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Scaffold(
        bottomBar = { RegisterBottomBar(onLoginClick = onLoginClick) },
    ) { paddingValues ->
        RegisterContent(
            modifier = Modifier.padding(paddingValues),
            formState = formState,
            onFormAction = onFormAction,
            onLoginClick = onLoginClick,
            onRegisterClick = onRegisterClick,
        )
    }
}
