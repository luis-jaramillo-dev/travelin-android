package com.projectlab.travelin_android.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.auth.domain.use_cases.AuthUseCases
import com.projectlab.core.domain.model.Response
import com.projectlab.core.domain.model.User
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.users.UsersUseCases
import com.projectlab.travelin_android.presentation.validation.AuthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val usersUseCases: UsersUseCases,
    private val authUseCases: AuthUseCases,
    private val userSessionProvider: UserSessionProvider,
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterUIState())
    val state: StateFlow<RegisterUIState> = _state.asStateFlow()

    init {
        val currentUser = authUseCases.getCurrentUser()

        if (currentUser != null) {
            _state.update { it.copy(success = true) }
        }
    }

    fun onFirstNameChange(value: String) {
        _state.update { it.copy(firstName = value) }
    }

    fun onLastNameChange(value: String) {
        _state.update { it.copy(lastName = value) }
    }

    fun onCountryCodeChange(value: String) {
        _state.update { it.copy(countryCode = value) }
    }

    fun onPhoneNumberChange(value: String) {
        _state.update {
            it.copy(
                phoneNumber = value,
                isPhoneNumberValid = AuthValidator.isPhoneNumberValid(value),
            )
        }
    }

    fun onAgeChange(value: String) {
        _state.update {
            it.copy(
                age = value,
                isAgeValid = AuthValidator.isAgeValid(value),
            )
        }
    }

    fun onEmailChange(value: String) {
        _state.update {
            it.copy(
                email = value,
                isEmailValid = AuthValidator.isEmailValid(value),
            )
        }
    }

    fun onPasswordChange(value: String) {
        _state.update {
            it.copy(
                password = value,
                isPasswordValid = AuthValidator.isPasswordValid(value),
            )
        }
    }

    fun onAcceptedTOSChange(value: Boolean) {
        _state.update { it.copy(acceptedTOS = value) }
    }

    fun register() {
        setAsLoading()

        viewModelScope.launch {
            val result = authUseCases.register(state.value.email, state.value.password)

            when (result) {
                is Response.Success -> {
                    createUser()

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

                is Response.Loading -> {}
            }
        }
    }

    private suspend fun createUser() {
        val currentUser = authUseCases.getCurrentUser()
        userSessionProvider.setUserSessionId(currentUser!!.uid)

        val newUser = User(
            id = currentUser.uid,
            email = state.value.email,
            age = state.value.age,
            firstName = state.value.firstName,
            lastName = state.value.lastName,
            countryCode = state.value.countryCode,
            phoneNumber = state.value.phoneNumber
        )

        usersUseCases.createUser(newUser)
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
