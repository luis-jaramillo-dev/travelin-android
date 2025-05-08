package com.projectlab.core.domain.entity

data class UserEntity(
    val firstName: String = "",
    val lastName: String = "",
    val countryCode: Int = 0,
    val phoneNumber: Int = 0,
    val email: String = ""
)
