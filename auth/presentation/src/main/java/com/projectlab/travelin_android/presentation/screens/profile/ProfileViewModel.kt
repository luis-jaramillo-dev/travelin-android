package com.projectlab.travelin_android.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.auth.domain.use_cases.AuthUseCases
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCases,
    private val usersUseCases: UsersUseCases
) : ViewModel() {
    val currentUser = authUseCase.getCurrentUser()

    var userEntityData by mutableStateOf(
        UserEntity(
            // id = EntityId(""), // TODO: check if we use EntityId or not
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

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserById(currentUser!!.uid).collect() {
            userEntityData = it
        }
    }

    fun logout() {
        authUseCase.logout()
    }
}