package com.projectlab.travelin_android.presentation.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.projectlab.travelin_android.presentation.validation.AuthValidator

class LoginViewModel(
) : ViewModel() {
    val email: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")

    val isEmailValid = derivedStateOf { AuthValidator.isEmailValid(email.value) }
    val emailError = derivedStateOf {
        if (email.value.isNotEmpty() && !isEmailValid.value) "Enter a valid email" else null
    }

    val isPasswordValid = derivedStateOf { AuthValidator.isPasswordValid(password.value) }
    val passwordError = derivedStateOf {
        if (password.value.isNotEmpty() && !isPasswordValid.value) "Password must be at least 6 characters, include an uppercase and a number" else null
    }

    val isFormValid = derivedStateOf {
        isEmailValid.value && isPasswordValid.value
    }


}