package com.projectlab.core.domain.entity

/**
 * FavoriteActivityEntity represents a favorite activity in the system.
 * @property apiId ID of the activity in the API.
 */
data class FavoriteActivityEntity(
    val apiId: String,
    val name: String,
    val description: String,
    val minimumDuration: String,
    val price: Float,
    val currency: String,
    val rating: Float,
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val pictures: List<String>,
)
