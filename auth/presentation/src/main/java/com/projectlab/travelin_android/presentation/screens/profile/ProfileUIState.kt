package com.projectlab.travelin_android.presentation.screens.profile

import com.projectlab.travelin_android.models.UserUI

data class ProfileUIState(
    val user: UserUI? = null,
    val loading: Boolean = false,
    val error: String? = null,
)
