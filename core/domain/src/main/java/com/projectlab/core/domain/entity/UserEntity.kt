package com.projectlab.core.domain.entity

data class UserEntity(
    var id: String = "",
    val email: String = "",
    val age: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val countryCode: String = "",
    val phoneNumber: String = "",
)