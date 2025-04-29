package com.projectlab.travelin_android.presentation.screens.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.projectlab.travelin_android.presentation.validation.AuthValidator

class RegisterViewModel (

) : ViewModel() {
    val firstName: MutableState<String> = mutableStateOf("")
    val lastName: MutableState<String> = mutableStateOf("")
    val countryCode: MutableState<String> = mutableStateOf("+56")
    val phoneNumber: MutableState<String> = mutableStateOf("")
    val age: MutableState<String> = mutableStateOf("")
    val email: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")
    val termsAndConditions: MutableState<Boolean> = mutableStateOf(false)


    val isAgeValid = derivedStateOf { AuthValidator.isAgeValid(age.value) }
    val ageError = derivedStateOf {
        if (age.value.isNotEmpty() && !isAgeValid.value) "Enter a valid age" else null
    }

    val isEmailValid = derivedStateOf { AuthValidator.isEmailValid(email.value) }
    val emailError = derivedStateOf {
        if (email.value.isNotEmpty() && !isEmailValid.value) "Enter a valid email" else null
    }

    val isPasswordValid = derivedStateOf { AuthValidator.isPasswordValid(password.value) }
    val passwordError = derivedStateOf {
        if (password.value.isNotEmpty() && !isPasswordValid.value) "Password must be at least 6 characters, include an uppercase and a number" else null
    }

    val isTermsAccepted = derivedStateOf { AuthValidator.isTermsAccepted(termsAndConditions.value) }
    val termsError = derivedStateOf {
        if (!termsAndConditions.value) "You must accept the terms and conditions" else null
    }

    val isFormValid = derivedStateOf {
        listOf(
            ageError.value,
            emailError.value,
            passwordError.value,
            termsError.value
        ).all { it == null } &&
                firstName.value.isNotBlank() &&
                lastName.value.isNotBlank() &&
                phoneNumber.value.isNotBlank()
    }
}