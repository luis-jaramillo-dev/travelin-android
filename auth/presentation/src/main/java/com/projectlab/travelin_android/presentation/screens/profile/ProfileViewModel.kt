package com.projectlab.travelin_android.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.auth.domain.use_cases.AuthUseCases
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.users.UsersUseCases
import com.projectlab.travelin_android.models.toUserUI
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
    private val _state = MutableStateFlow(ProfileUIState())
    val state: StateFlow<ProfileUIState> = _state.asStateFlow()

    init {
        getUserById()
    }

    fun logout() {
        setAsLoading()

        viewModelScope.launch {
            userSessionProvider.deleteUserSession()
            authUseCase.logout()
            _state.update { it.copy(loading = false) }
        }
    }

    private fun getUserById() {
        setAsLoading()

        viewModelScope.launch {
            val currentUser = authUseCase.getCurrentUser()
            usersUseCases.getUserById(currentUser!!.uid).collect { user ->
                _state.update { it.copy(loading = false, user = user.toUserUI()) }
            }
        }
    }

    private fun setAsLoading() {
        _state.update {
            it.copy(
                loading = true,
                error = null,
                user = null,
            )
        }
    }
}
