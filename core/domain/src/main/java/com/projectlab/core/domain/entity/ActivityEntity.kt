package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId
import java.time.Instant

/**
 * ActivityEntity represents an activity in the system.
 * @property id Unique identifier for the activity.
 * @property name Name of the activity.
 * @property locationRef Reference to the location associated with the activity.
 * @property activityDate Date of the activity.
 * @property details Details about the activity.
 * @property activityPrice Price of the activity.
 * @property userRef Reference to the user associated with the itinerary, that is associated with
 * the activity.
 * @property itineraryRef Reference to the itinerary associated with the activity.
 *
 * @author ricardoceadev
 */

data class ActivityEntity(
    val id : String = "",
    val name : String = "",
    val locationRef : EntityId? = null,
    val activityDate : Instant,
    val details : String = "",
    val activityPrice : Double = 0.0,
    val userRef : EntityId? = null,
    val itineraryRef : EntityId? = null
)
