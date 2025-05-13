package com.projectlab.core.data.mapper

import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.data.model.LocationDataDto
import com.projectlab.core.data.model.PriceDto

fun ActivityDto.toDomain(): Activity {
    return Activity(
        id = id,
        name = name ?: "Unknown",
        description = description ?: "",
        latitude = geoCode.latitude ?: 0.0,
        longitude = geoCode.longitude ?: 0.0,
        price = price.amount ?: "",
        currency = price.currencyCode ?: "",
        pictures = pictures ?: emptyList(),
        minimumDuration = minimumDuration ?: "",
        rating = rating ?: 0f
    )
}

fun List<ActivityDto>.toDomainList(): List<Activity> {
    return map { it.toDomain() }
}

fun Activity.toDto(): ActivityDto {
    return ActivityDto(
        id = id,
        name = name,
        description = description,
        geoCode = LocationDataDto(latitude, longitude),
        price = PriceDto(price, currency),
        pictures = pictures,
        minimumDuration = minimumDuration,
        rating = rating)
}

fun List<Activity>.toDtoList(): List<ActivityDto> {
    return map { it.toDto() }
}