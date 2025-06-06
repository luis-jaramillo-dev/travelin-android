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
import com.projectlab.travelin_android.presentation.screens.register.RegisterViewModel

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
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

        RegisterForm(viewModel = viewModel)

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        ButtonSimple(
            text = stringResource(R.string.create_account_button),
            onClick = onRegisterClick,
            enabled = viewModel.isFormValid.value,
        )
    }
}
