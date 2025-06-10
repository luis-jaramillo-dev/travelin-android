package com.projectlab.travelin_android.presentation.screens.login

import com.projectlab.core.domain.util.DataError

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val loading: Boolean = false,
    val error: DataError.FirebaseAuth? = null,
    val isError: Boolean = false,
    val success: Boolean = false,
)
