package com.projectlab.travelin_android.presentation.screens.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.projectlab.travelin_android.presentation.components.DropdownOutlinedButton
import com.projectlab.travelin_android.presentation.components.OutlinedTextFieldPassword
import com.projectlab.travelin_android.presentation.components.OutlinedTextFieldSimple
import com.projectlab.travelin_android.presentation.screens.register.RegisterViewModel

@Composable
fun RegisterForm(
    viewModel: RegisterViewModel
) {

    val phoneCountryCodes = listOf(
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
        "+34"
    )

    Column {
        OutlinedTextFieldSimple(
            label = "First name",
            placeholderText = "Enter your first name",
            value = viewModel.firstName.value,
            onValueChange = { value -> viewModel.firstName.value = value },
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextFieldSimple(
            label = "Last name",
            placeholderText = "Enter your last name",
            value = viewModel.lastName.value,
            onValueChange = { value -> viewModel.lastName.value = value },
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Phone",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DropdownOutlinedButton(
                label = "",
                placeholder = "+56",
                options = phoneCountryCodes,
                selectedOption = viewModel.countryCode,
                modifier = Modifier.weight(0.38f)
            )
            Spacer(modifier = Modifier.width(4.dp))
            OutlinedTextFieldSimple(
                label = "",
                placeholderText = "Enter your phone number",
                value = viewModel.phoneNumber.value,
                onValueChange = { value -> viewModel.phoneNumber.value = value },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(0.62f)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextFieldSimple(
            label = "Age",
            placeholderText = "Enter your age",
            value = viewModel.age.value,
            onValueChange = { value -> viewModel.age.value = value },
            isError = viewModel.ageError.value != null,
            errorMessage = viewModel.ageError.value,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextFieldSimple(
            label = "Email",
            placeholderText = "Enter your email address",
            value = viewModel.email.value,
            onValueChange = { value -> viewModel.email.value = value },
            isError = viewModel.emailError.value != null,
            errorMessage = viewModel.emailError.value,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextFieldPassword(
            label = "Password",
            placeholderText = "Enter your password",
            value = viewModel.password.value,
            onValueChange = { value -> viewModel.password.value = value },
            isError = viewModel.passwordError.value != null,
            errorMessage = viewModel.passwordError.value,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            modifier = Modifier
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = viewModel.termsAndConditions.value,
                onCheckedChange = { viewModel.termsAndConditions.value = it },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = MaterialTheme.colorScheme.outline,
                    checkmarkColor = MaterialTheme.colorScheme.primary,
                    checkedColor = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = buildAnnotatedString {
                    append("")
                    pushStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                    append("I accept term and condition")
                    pop()
                },
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
