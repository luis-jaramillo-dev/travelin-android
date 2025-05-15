package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId

/**
 * UserEntity represents a user in the system.
 * @property id Unique identifier for the user.
 * @property email Email address of the user.
 * @property age Age of the user.
 * @property firstName First name of the user.
 * @property lastName Last name of the user.
 * @property countryCode Country code of the user's phone number.
 * @property phoneNumber Phone number of the user.
 */

data class UserEntity(
    val id : String = "",
    val email: String = "",
    val age: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val countryCode: String = "",
    val phoneNumber: String = "",
)