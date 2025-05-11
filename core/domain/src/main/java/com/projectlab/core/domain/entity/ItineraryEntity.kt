package com.projectlab.core.domain.entity
import com.projectlab.core.domain.model.EntityId
import java.time.Instant


data class ItineraryEntity(
    val id : EntityId? = null,
    val title: String = "",
    val startDate : Instant,
    val endDate : Instant,
    val totalItineraryPrice: Double = 0.0,
    val userRef : EntityId? = null,
)
