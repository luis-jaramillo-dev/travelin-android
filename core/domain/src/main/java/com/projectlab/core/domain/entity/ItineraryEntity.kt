package com.projectlab.core.domain.entity
import com.projectlab.core.domain.model.EntityId
import java.time.Instant


data class ItineraryEntity(
    // TODO: check if we need to use EntityId (and in others entities) instead of String
    //val id : EntityId? = null,
    val id : String = "",
    val title: String = "",
    val startDate : Instant,
    val endDate : Instant,
    val totalItineraryPrice: Double = 0.0,
    val userRef : EntityId? = null,
)
