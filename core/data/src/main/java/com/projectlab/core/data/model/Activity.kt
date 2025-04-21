package com.projectlab.core.data.model

data class Activity(
    val id: String,
    val name: String,
    val description: String,
    val pictures: List<String>,
    val minimumDuration: String,
    val price: Price,
    val rating: Float
)