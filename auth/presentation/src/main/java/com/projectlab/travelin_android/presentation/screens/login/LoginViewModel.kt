package com.projectlab.travelin_android.presentation.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.projectlab.auth.domain.use_cases.AuthUseCases
import com.projectlab.core.domain.model.Response
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.travelin_android.presentation.validation.AuthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCases,
    private val userSessionProvider: UserSessionProvider,
) : ViewModel() {
    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(value = null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow

    val email: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")

    val isEmailValid = derivedStateOf { AuthValidator.isEmailValid(email.value) }
    val isFormValid = derivedStateOf { isEmailValid.value }

    val currentUser = authUseCase.getCurrentUser()

    init {
        if (currentUser != null) {
            _loginFlow.value = Response.Loading

            viewModelScope.launch {
                userSessionProvider.setUserSessionId(currentUser.uid)
                _loginFlow.value = Response.Success(currentUser)
            }
        }
    }

    fun login() = viewModelScope.launch {
        _loginFlow.value = Response.Loading
        val result = authUseCase.login(email.value, password.value)
        _loginFlow.value = result
    }
}
