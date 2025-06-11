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
import com.projectlab.travelin_android.presentation.screens.register.FormAction
import com.projectlab.travelin_android.presentation.screens.register.FormState

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    formState: FormState,
    onFormAction: (FormAction) -> Unit,
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
            formState = formState,
            onFormAction = onFormAction,
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        ButtonSimple(
            modifier = Modifier
                .height(MaterialTheme.spacing.FieldHeight),
            text = stringResource(R.string.create_account_button),
            onClick = onRegisterClick,
            enabled = formState.firstName.isNotEmpty()
                && formState.lastName.isNotEmpty()
                && formState.countryCode.isNotEmpty()
                && formState.phoneNumber.isNotEmpty()
                && formState.age.isNotEmpty()
                && formState.email.isNotEmpty()
                && formState.password.isNotEmpty()
                && formState.acceptedTOS
                && formState.isPhoneNumberValid
                && formState.isAgeValid
                && formState.isEmailValid
                && formState.isPasswordValid,
        )
    }
}
