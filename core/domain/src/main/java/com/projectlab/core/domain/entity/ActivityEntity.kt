package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId
import java.time.Instant

data class ActivityEntity(
    val id : EntityId? = null,
    val name : String = "",
    val locationRef : EntityId? = null,
    val activityDate : Instant,
    val details : String = "",
    val activityPrice : Double = 0.0,
    val userRef : EntityId? = null,
    val itineraryRef : EntityId? = null
)
