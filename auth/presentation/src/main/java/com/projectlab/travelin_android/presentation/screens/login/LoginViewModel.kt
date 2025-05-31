package com.projectlab.travelin_android.presentation.screens.login

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.projectlab.auth.domain.use_cases.AuthUseCases
import com.projectlab.core.domain.model.Response
import com.projectlab.travelin_android.presentation.validation.AuthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCases
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(value = null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow


    var state by mutableStateOf(LoginState())
        private set

    val isEmailValid = derivedStateOf { AuthValidator.isEmailValid(state.email.value) }
    val emailError = derivedStateOf {
        if (state.email.value.isNotEmpty() && !isEmailValid.value) "Enter a valid email" else null
    }

    val isPasswordValid = derivedStateOf { AuthValidator.isPasswordValid(state.password.value) }
    val passwordError = derivedStateOf {
        if (state.password.value.isNotEmpty() && !isPasswordValid.value) "Password must be at least 6 characters, include an uppercase and a number" else null
    }

    val isFormValid = derivedStateOf {
        isEmailValid.value && isPasswordValid.value
    }

    val currentUser = authUseCase.getCurrentUser()

    init {
        if (currentUser != null) {
            _loginFlow.value = Response.Success(currentUser)
        }
    }

    fun login() = viewModelScope.launch {
        _loginFlow.value = Response.Loading
        val result = authUseCase.login(state.email.value, state.password.value)
        _loginFlow.value = result
    }
}