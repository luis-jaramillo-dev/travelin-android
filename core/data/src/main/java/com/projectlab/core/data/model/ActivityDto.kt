package com.projectlab.core.data.model

data class ActivityDto(
    val id: String,
    val name: String,
    val description: String,
    val geoCode: LocationDataDto,
    val price: PriceDto,
    val pictures: List<String>,
    val minimumDuration: String,
    val rating: Float
)
