package com.projectlab.travelin_android.presentation.screens.register

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.projectlab.auth.domain.use_cases.AuthUseCases
import com.projectlab.core.domain.model.Response
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.use_cases.users.UsersUseCases
import com.projectlab.travelin_android.presentation.validation.AuthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val usersUseCases: UsersUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    val isAgeValid = derivedStateOf { AuthValidator.isAgeValid(state.age.value) }
    val ageError = derivedStateOf {
        if (state.age.value.isNotEmpty() && !isAgeValid.value) "Enter a valid age" else null
    }

    val isEmailValid = derivedStateOf { AuthValidator.isEmailValid(state.email.value) }
    val emailError = derivedStateOf {
        if (state.email.value.isNotEmpty() && !isEmailValid.value) "Enter a valid email" else null
    }

    val isPasswordValid = derivedStateOf { AuthValidator.isPasswordValid(state.password.value) }
    val passwordError = derivedStateOf {
        if (state.password.value.isNotEmpty() && !isPasswordValid.value) "Password must be at least 6 characters, include an uppercase and a number" else null
    }

    val isTermsAccepted =
        derivedStateOf { AuthValidator.isTermsAccepted(state.termsAndConditions.value) }
    val termsError = derivedStateOf {
        if (!state.termsAndConditions.value) "You must accept the terms and conditions" else null
    }

    val isFormValid = derivedStateOf {
        listOf(
            ageError.value,
            emailError.value,
            passwordError.value,
            termsError.value
        ).all { it == null } &&
                state.firstName.value.isNotBlank() &&
                state.lastName.value.isNotBlank() &&
                state.phoneNumber.value.isNotBlank()
    }

    private val _registerFlow = MutableStateFlow<Response<FirebaseUser>?>(value = null)
    val registerFlow: StateFlow<Response<FirebaseUser>?> = _registerFlow

    init {
        val currentUser = authUseCases.getCurrentUser()

        if (currentUser != null) {
            _registerFlow.value = Response.Success(currentUser)
        }
    }

    fun register() = viewModelScope.launch {
        _registerFlow.value = Response.Loading
        val result = authUseCases.register(state.email.value, state.password.value)
        _registerFlow.value = result
    }

    fun createUser() = viewModelScope.launch {
        val currentUser = authUseCases.getCurrentUser()

        val newUserEntity = UserEntity(
            id = currentUser!!.uid,
            email = state.email.value,
            age = state.age.value,
            firstName = state.firstName.value,
            lastName = state.lastName.value,
            countryCode = state.countryCode.value,
            phoneNumber = state.phoneNumber.value
        )
        usersUseCases.createUser(newUserEntity)
    }
}