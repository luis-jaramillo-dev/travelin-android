package com.projectlab.travelin_android.presentation.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LoginState(
    val email: MutableState<String> = mutableStateOf(""),
    val password: MutableState<String> = mutableStateOf("")
)