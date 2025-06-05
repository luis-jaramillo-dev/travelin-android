package com.projectlab.travelin_android.models

import com.projectlab.core.domain.model.User

data class UserUi(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val age: String
)

fun User.toUserUi(): UserUi {
    return UserUi(
        id = id,
        name = "$firstName $lastName",
        email = email,
        phoneNumber = "$countryCode $phoneNumber",
        age = age
    )
}