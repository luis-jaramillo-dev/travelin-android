package com.projectlab.core.data.mapper

import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.data.model.LocationDataDto
import com.projectlab.core.data.model.PriceDto
import com.projectlab.core.domain.entity.FavoriteActivityEntity

/**
 * Extension function to convert an ActivityDto to a domain model Activity.
 * This is used to map the data received from the API to the domain model used in the application.
 *
 * @return An Activity object with the same properties as the ActivityDto.
 */
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

/**
 * Converts a list of ActivityDto objects to a list of Activity domain models.
 */
fun List<ActivityDto>.toDomainList(): List<Activity> {
    return map { it.toDomain() }
}

/**
 * Extension function to convert an Activity domain model to an ActivityDto.
 * This is used to map the domain model back to the data transfer object for API communication.
 *
 * @return An ActivityDto object with the same properties as the Activity.
 */
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

/**
 * Converts a list of Activity domain models to a list of ActivityDto objects.
 */
fun List<Activity>.toDtoList(): List<ActivityDto> {
    return map { it.toDto() }
}

fun ActivityDto.toFavoriteActivityEntity(): FavoriteActivityEntity {
    return FavoriteActivityEntity(
        id = id,
        name = name,
        description = description,
        minimumDuration = minimumDuration,
        price = price.amount,
        currency = price.currencyCode,
        rating = rating,
        location = geoCode.toString(),
        latitude = geoCode.latitude,
        longitude = geoCode.longitude,
        pictures = pictures

    )
}