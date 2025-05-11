package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId


data class LocationEntity(
    val id : EntityId? = null,
    val name : String = "",
    val country : String = "",
    val countryCode : String = "",
    val coordinates : String = "",
    val timeZone : String = "",
    val region: String = ""
)
