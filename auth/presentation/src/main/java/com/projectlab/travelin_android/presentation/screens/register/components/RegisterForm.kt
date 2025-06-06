package com.projectlab.travelin_android.presentation.screens.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import com.projectlab.auth.presentation.R
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.travelin_android.presentation.components.DropdownOutlinedButton
import com.projectlab.travelin_android.presentation.components.OutlinedTextFieldPassword
import com.projectlab.travelin_android.presentation.components.OutlinedTextFieldSimple
import com.projectlab.travelin_android.presentation.screens.register.RegisterViewModel

private val phoneCountryCodes = listOf(
    "+1",
    "+44",
    "+33",
    "+49",
    "+81",
    "+82",
    "+91",
    "+55",
    "+61",
    "+86",
    "+7",
    "+52",
    "+56",
    "+57",
    "+58",
    "+54",
    "+90",
    "+34",
    "+123",
)

@Composable
fun RegisterForm(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ElementSpacing),
    ) {
        OutlinedTextFieldSimple(
            label = stringResource(R.string.first_name),
            placeholderText = stringResource(R.string.enter_your_first_name),
            value = viewModel.firstName.value,
            onValueChange = { value -> viewModel.firstName.value = value },
        )

        OutlinedTextFieldSimple(
            label = stringResource(R.string.last_name),
            placeholderText = stringResource(R.string.enter_your_last_name),
            value = viewModel.lastName.value,
            onValueChange = { value -> viewModel.lastName.value = value },
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
        ) {
            DropdownOutlinedButton(
                label = stringResource(R.string.phone),
                placeholder = "+56",
                options = phoneCountryCodes,
                selectedOption = viewModel.countryCode,
                modifier = Modifier.weight(0.30f),
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

            OutlinedTextFieldSimple(
                label = "",
                placeholderText = stringResource(R.string.enter_your_phone_number),
                value = viewModel.phoneNumber.value,
                onValueChange = { value -> viewModel.phoneNumber.value = value },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(0.70f),
            )
        }

        OutlinedTextFieldSimple(
            label = stringResource(R.string.age),
            placeholderText = stringResource(R.string.enter_your_age),
            value = viewModel.age.value,
            onValueChange = { value -> viewModel.age.value = value },
            isError = viewModel.ageError.value != null,
            errorMessage = viewModel.ageError.value,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )

        OutlinedTextFieldSimple(
            label = stringResource(R.string.email),
            placeholderText = stringResource(R.string.enter_your_email_address),
            value = viewModel.email.value,
            onValueChange = { value -> viewModel.email.value = value },
            isError = viewModel.emailError.value != null,
            errorMessage = viewModel.emailError.value,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        )

        OutlinedTextFieldPassword(
            label = stringResource(R.string.password),
            placeholderText = stringResource(R.string.enter_your_password),
            value = viewModel.password.value,
            onValueChange = { value -> viewModel.password.value = value },
            isError = viewModel.passwordError.value != null,
            errorMessage = viewModel.passwordError.value,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = viewModel.termsAndConditions.value,
                onCheckedChange = { viewModel.termsAndConditions.value = it },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = MaterialTheme.colorScheme.outline,
                    checkmarkColor = MaterialTheme.colorScheme.primary,
                    checkedColor = MaterialTheme.colorScheme.primary,
                ),
            )

            Text(
                text = stringResource(R.string.i_accept_the_terms_and_conditions),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    textDecoration = TextDecoration.Underline,
                ),
            )
        }
    }
}
