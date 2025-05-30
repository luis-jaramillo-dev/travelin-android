package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId

/**
 * FavoriteActivityEntity represents a favorite activity in the system.
 * @property apiId ID of the activity in the API.
 * @property locationRef Reference to the location associated with the activity.
 * @property userRef Reference to the user that is associated with the activity.
 */
data class FavoriteActivityEntity(
    val apiId: String,
    val name: String,
    val description: String,
    val minimumDuration: String,
    val price: Float,
    val currency: String,
    val rating: Float,
    val locationRef: EntityId,
    val userRef: EntityId,
)
