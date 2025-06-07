package com.projectlab.travelin_android.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.auth.domain.use_cases.AuthUseCases
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.users.UsersUseCases
import com.projectlab.travelin_android.models.toUserUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCases,
    private val usersUseCases: UsersUseCases,
    private val userSessionProvider: UserSessionProvider,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    init {
        getUserById()
    }

    fun logout() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            userSessionProvider.deleteUserSession()
            authUseCase.logout()
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun getUserById() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        val currentUser = authUseCase.getCurrentUser()
        usersUseCases.getUserById(currentUser!!.uid).collect { user ->
            _state.update { it.copy(userUi = user.toUserUi()) }
        }
        _state.update { it.copy(isLoading = false) }
    }
}