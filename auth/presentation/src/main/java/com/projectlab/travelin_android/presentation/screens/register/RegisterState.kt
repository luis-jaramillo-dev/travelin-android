package com.projectlab.travelin_android.presentation.screens.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class RegisterState(
    val firstName: MutableState<String> = mutableStateOf(""),
    val lastName: MutableState<String> = mutableStateOf(""),
    val countryCode: MutableState<String> = mutableStateOf("+56"),
    val phoneNumber: MutableState<String> = mutableStateOf(""),
    val age: MutableState<String> = mutableStateOf(""),
    val email: MutableState<String> = mutableStateOf(""),
    val password: MutableState<String> = mutableStateOf(""),
    val termsAndConditions: MutableState<Boolean> = mutableStateOf(false)
)