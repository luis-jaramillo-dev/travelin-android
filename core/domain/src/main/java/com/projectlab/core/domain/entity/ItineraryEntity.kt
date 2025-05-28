package com.projectlab.core.domain.entity
import com.projectlab.core.domain.model.EntityId
import java.time.Instant
 /**
 * ItineraryEntity represents a travel itinerary in the system.
 * @property id Unique identifier for the itinerary.
 * @property title Title of the itinerary.
 * @property startDate Start date of the itinerary.
 * @property endDate End date of the itinerary.
 * @property totalItineraryPrice Total price of the itinerary.
 * @property userRef Reference to the user associated with the itinerary.
  *
  * @author ricardoceadev
 */

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
