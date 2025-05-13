package com.projectlab.core.presentation.ui.model

data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val city: String? = null,
    val country: String? = null
)
