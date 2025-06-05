package com.projectlab.travelin_android.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.auth.domain.use_cases.AuthUseCases
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.model.User
import com.projectlab.core.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCases,
    private val usersUseCases: UsersUseCases,
    private val userSessionProvider: UserSessionProvider,
) : ViewModel() {
    val currentUser = authUseCase.getCurrentUser()

    var user by mutableStateOf(
        User(
            id = "",
            email = "",
            age = "",
            firstName = "",
            lastName = "",
            countryCode = "",
            phoneNumber = ""
        )
    )
        private set

    init {
        getUserById()
    }

    fun logout() {
        viewModelScope.launch {
            userSessionProvider.deleteUserSession()
        }
        authUseCase.logout()
    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserById(currentUser!!.uid).collect {
            user = it
        }
    }
}
