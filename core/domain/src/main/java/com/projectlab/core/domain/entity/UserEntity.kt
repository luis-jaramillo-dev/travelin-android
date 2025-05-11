package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId

data class UserEntity(
    // var id: String = "",
    val id : EntityId? = null,
    val email: String = "",
    val age: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val countryCode: String = "",
    val phoneNumber: String = "",
)