package com.projectlab.travelin_android.presentation.screens.login

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null,
    val isError: Boolean = false,
    val success: Boolean = false,
)
