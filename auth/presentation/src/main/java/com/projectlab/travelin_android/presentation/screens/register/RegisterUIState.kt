package com.projectlab.travelin_android.presentation.screens.register

data class RegisterUIState(
    val firstName: String = "",
    val lastName: String = "",
    val countryCode: String = "+56",
    val phoneNumber: String = "",
    val age: String = "",
    val email: String = "",
    val password: String = "",
    val isAgeValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val acceptedTOS: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null,
    val isError: Boolean = false,
    val success: Boolean = false,
)
