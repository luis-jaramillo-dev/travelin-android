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

    fun onFormAction(action: FormAction) {
        when (action) {
            is FormAction.OnFirstNameChange -> {
                _state.update {
                    it.copy(formState = it.formState.copy(firstName = action.value))
                }
            }

            is FormAction.OnLastNameChange -> {
                _state.update {
                    it.copy(formState = it.formState.copy(lastName = action.value))
                }
            }

            is FormAction.OnCountryCodeChange -> {
                _state.update {
                    it.copy(formState = it.formState.copy(countryCode = action.value))
                }
            }

            is FormAction.OnPhoneNumberChange -> {
                _state.update {
                    it.copy(
                        formState = it.formState.copy(
                            phoneNumber = action.value,
                            isPhoneNumberValid = AuthValidator.isPhoneNumberValid(action.value),
                        )
                    )
                }
            }

            is FormAction.OnAgeChange -> {
                _state.update {
                    it.copy(
                        formState = it.formState.copy(
                            age = action.value,
                            isAgeValid = AuthValidator.isAgeValid(action.value),
                        )
                    )
                }
            }

            is FormAction.OnEmailChange -> {
                _state.update {
                    it.copy(
                        formState = it.formState.copy(
                            email = action.value,
                            isEmailValid = AuthValidator.isEmailValid(action.value),
                        )
                    )
                }
            }

            is FormAction.OnPasswordChange -> {
                _state.update {
                    it.copy(
                        formState = it.formState.copy(
                            password = action.value,
                            isPasswordValid = AuthValidator.isPasswordValid(action.value),
                        )
                    )
                }
            }

            is FormAction.OnAcceptedTOSChange -> {
                _state.update {
                    it.copy(formState = it.formState.copy(acceptedTOS = action.value))
                }
            }
        }
    }

    fun register() {
        setAsLoading()

        viewModelScope.launch {
            val formState = state.value.formState
            val result = authUseCases.register(formState.email, formState.password)

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
            email = state.value.formState.email,
            age = state.value.formState.age,
            firstName = state.value.formState.firstName,
            lastName = state.value.formState.lastName,
            countryCode = state.value.formState.countryCode,
            phoneNumber = state.value.formState.phoneNumber
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
