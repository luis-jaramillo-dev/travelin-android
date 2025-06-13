package com.projectlab.core.domain.entity

data class FavoriteHotelEntity(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val location: String = "",
    val rating: Double = 0.0,
    val displayImageUrl: String = "",
    val amenities: List<String> = emptyList(),
    val address: String = "",
    val priceRange: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis()
)