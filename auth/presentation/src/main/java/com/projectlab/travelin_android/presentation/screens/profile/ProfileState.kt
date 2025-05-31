package com.projectlab.travelin_android.presentation.screens.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.projectlab.core.domain.entity.UserEntity

data class ProfileState(
    val userEntityData: MutableState<UserEntity> = mutableStateOf(
        UserEntity()
    )
)