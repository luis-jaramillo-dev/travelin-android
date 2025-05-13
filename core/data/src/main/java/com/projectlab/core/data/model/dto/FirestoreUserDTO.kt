package com.projectlab.core.data.model.dto

data class FirestoreUserDTO (
    val firstName: String = "",
    val lastName: String = "",
    val countryCode: String = "",
    val phoneNumber: String = "",
    val email: String = ""
) {

    companion object {
        fun fromDomain(user: com.projectlab.core.domain.entity.UserEntity): FirestoreUserDTO =
            FirestoreUserDTO (
                firstName = user.firstName,
                lastName = user.lastName,
                countryCode = user.countryCode,
                phoneNumber = user.phoneNumber,
                email = user.email
            )
    }
    fun toDomain(id : String): com.projectlab.core.domain.entity.UserEntity =
        com.projectlab.core.domain.entity.UserEntity(
            id = com.projectlab.core.domain.model.EntityId(id),
            firstName = firstName,
            lastName = lastName,
            countryCode = countryCode,
            phoneNumber = phoneNumber,
            email = email
        )
}

