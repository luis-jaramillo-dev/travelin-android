package com.projectlab.core.domain.entity

/**
 * FavoriteActivityEntity represents a favorite activity in the system.
 * @property id ID of the activity in the API.
 */
data class FavoriteActivityEntity(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val minimumDuration: String = "",
    val price: String = "",
    val currency: String = "",
    val rating: Float = 0f,
    val location: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val pictures: List<String> = emptyList(),
)
