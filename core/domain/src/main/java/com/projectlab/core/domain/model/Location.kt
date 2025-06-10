package com.projectlab.core.domain.model

data class Location(
    var latitude: Double,
    var longitude: Double,
    val city: String? = null,
    val country: String? = null
)
