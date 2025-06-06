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
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ElementSpacing),
    ) {
        OutlinedTextFieldSimple(
            label = stringResource(R.string.first_name),
            placeholderText = stringResource(R.string.enter_your_first_name),
            value = firstName,
            onValueChange = onFirstNameChange,
        )

        OutlinedTextFieldSimple(
            label = stringResource(R.string.last_name),
            placeholderText = stringResource(R.string.enter_your_last_name),
            value = lastName,
            onValueChange = onLastNameChange,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
        ) {
            DropdownOutlinedButton(
                label = stringResource(R.string.phone),
                placeholder = "+56",
                options = phoneCountryCodes,
                selectedOption = countryCode,
                onSelectedOption = onCountryCodeChange,
                modifier = Modifier.weight(0.30f),
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

            OutlinedTextFieldSimple(
                label = "",
                placeholderText = stringResource(R.string.enter_your_phone_number),
                value = phoneNumber,
                onValueChange = onPhoneNumberChange,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(0.70f),
            )
        }

        OutlinedTextFieldSimple(
            label = stringResource(R.string.age),
            placeholderText = stringResource(R.string.enter_your_age),
            value = age,
            onValueChange = onAgeChange,
            isError = age.isNotEmpty() && !isAgeValid,
            errorMessage = stringResource(R.string.enter_a_valid_age),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )

        OutlinedTextFieldSimple(
            label = stringResource(R.string.email),
            placeholderText = stringResource(R.string.enter_your_email_address),
            value = email,
            onValueChange = onEmailChange,
            isError = email.isNotEmpty() && !isEmailValid,
            errorMessage = stringResource(R.string.enter_a_valid_email),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        )

        OutlinedTextFieldPassword(
            label = stringResource(R.string.password),
            placeholderText = stringResource(R.string.enter_your_password),
            value = password,
            onValueChange = onPasswordChange,
            isError = password.isNotEmpty() && !isPasswordValid,
            errorMessage = stringResource(R.string.enter_a_valid_password),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = acceptedTOS,
                onCheckedChange = onAcceptedTOSChange,
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
