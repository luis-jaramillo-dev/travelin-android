package com.projectlab.core.data.model.dto

/**
 *
 * Data Transfer Object (DTO) for Firestore User.
 *
 * @property firstName The first name of the user.
 * @property lastName The last name of the user.
 * @property countryCode The country code of the user's phone number.
 * @property phoneNumber The phone number of the user.
 * @property email The email address of the user.
 * @property age The age of the user.
 *
 * @author ricardoceadev
 */

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

