package com.projectlab.core.data.mapper

import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.domain.model.Activity

fun ActivityDto.toDomain(): Activity {
    return Activity(
        id = id,
        name = name ?: "Unknown",
        description = description ?: "",
        latitude = geoCode.latitude ?: 0.0,
        longitude = geoCode.longitude ?: 0.0,
        price = price.amount ?: "",
        currency = price.currencyCode ?: "USD",
        pictures = pictures ?: emptyList(),
        minimumDuration = minimumDuration ?: "",
        rating = rating ?: 0f
    )
}

fun List<ActivityDto>.toDomainList(): List<Activity> {
    return map { it.toDomain() }
}