package com.projectlab.core.data.model.dto

data class FirestoreUserDTO (
    val firstName: String = "",
    val lastName: String = "",
    val countryCode: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val age: String = ""
) {

    companion object {
        fun fromDomain(user: com.projectlab.core.domain.entity.UserEntity): FirestoreUserDTO =
            FirestoreUserDTO (
                firstName = user.firstName,
                lastName = user.lastName,
                countryCode = user.countryCode,
                phoneNumber = user.phoneNumber,
                email = user.email,
                age = user.age
            )
    }
    fun toDomain(id : String): com.projectlab.core.domain.entity.UserEntity =
        com.projectlab.core.domain.entity.UserEntity(
            id = id,
            firstName = firstName,
            lastName = lastName,
            countryCode = countryCode,
            phoneNumber = phoneNumber,
            email = email,
            age = age
        )
}

