package com.projectlab.core.domain.model

data class Activity(
    val id: String,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val price: String,
    val currency: String,
    val pictures: List<String>,
    val minimumDuration: String,
    val rating: Float
)