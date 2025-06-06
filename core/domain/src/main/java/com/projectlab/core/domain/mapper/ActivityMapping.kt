package com.projectlab.core.domain.mapper

import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.model.EntityId
import java.time.Instant

fun Activity.toEntity (
    userId: String,
    itineraryId: String,
) : ActivityEntity {
    return ActivityEntity(
        id = "",
        name = this.name,
        latitude = this.latitude,
        longitude = this.longitude,
        activityDate = TODO(), // We need date information from amadeus
        description = this.description,
        amount = this.price,
        currencyCode = this.currency,
        userRef = EntityId(userId),
        itineraryRef = EntityId(itineraryId)
    )
}