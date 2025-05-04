package com.projectlab.core.domain.model

data class Activity(
    val id: String,
    val name: String,
    val description: String,
    val pictures: List<String>,
    val minimumDuration: String,
    val price: com.projectlab.core.domain.model.Price,
    val rating: Float
)