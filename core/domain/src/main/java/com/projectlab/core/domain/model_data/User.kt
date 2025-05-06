package com.projectlab.core.domain.model_data

data class User(
    val firstName: String = "",
    val lastName: String = "",
    val countryCode: Int = 0,
    val phoneNumber: Int = 0,
    val email: String = ""
)
