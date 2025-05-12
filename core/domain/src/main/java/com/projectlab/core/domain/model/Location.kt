package com.projectlab.core.domain.model

data class Location(
    val latitude: Double,
    val longitude: Double,
    val city: String? = null,
    val country: String? = null
)
