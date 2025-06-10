package com.projectlab.travelin_android.presentation.screens.register

import com.projectlab.core.domain.util.DataError

data class RegisterUIState(
    val formState: FormState = FormState(),
    val loading: Boolean = false,
    val error: DataError.FirebaseAuth? = null,
    val isError: Boolean = false,
    val success: Boolean = false,
)

data class FormState(
    val firstName: String = "",
    val lastName: String = "",
    val countryCode: String = "",
    val phoneNumber: String = "",
    val age: String = "",
    val email: String = "",
    val password: String = "",
    val isPhoneNumberValid: Boolean = false,
    val isAgeValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val acceptedTOS: Boolean = false,
)
