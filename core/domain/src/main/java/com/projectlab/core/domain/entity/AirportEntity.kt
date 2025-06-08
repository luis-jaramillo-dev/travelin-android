package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId

data class AirportEntity(
    val id : EntityId? = null,
    val airportCode : String = "",
    val name : String = "",
    val coordinates : String = "",
    val city : String = "",
    val country : String = "",
    val timeZone : String = "",
    // val locationRef : EntityId
)
