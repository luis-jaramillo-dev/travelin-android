package com.projectlab.travelin_android.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.auth.domain.use_cases.AuthUseCases
import com.projectlab.core.domain.model.Response
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.travelin_android.presentation.validation.AuthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCases,
    private val userSessionProvider: UserSessionProvider,
) : ViewModel() {
    private val _state = MutableStateFlow(LoginUIState())
    val state: StateFlow<LoginUIState> = _state.asStateFlow()

    init {
        val currentUser = authUseCase.getCurrentUser()

        if (currentUser != null) {
            setAsLoading()

            viewModelScope.launch {
                userSessionProvider.setUserSessionId(currentUser.uid)
                _state.update { it.copy(loading = false, success = true) }
            }
        }
    }

    fun onEmailChange(email: String) {
        _state.update {
            it.copy(
                email = email,
                isEmailValid = AuthValidator.isEmailValid(email),
            )
        }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun login() {
        setAsLoading()

        viewModelScope.launch {
            val result = authUseCase.login(state.value.email, state.value.password)

            when (result) {
                is Response.Success -> {
                    userSessionProvider.setUserSessionId(result.data.uid)
                    _state.update { it.copy(loading = false, success = true) }
                }

                is Response.Failure -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            isError = true,
                            error = result.exception?.localizedMessage,
                        )
                    }
                }

                else -> {}
            }
        }
    }

    private fun setAsLoading() {
        _state.update {
            it.copy(
                loading = true,
                isError = false,
                error = null,
                success = false,
            )
        }
    }
}
