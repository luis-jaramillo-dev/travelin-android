package com.projectlab.core.domain.entity
import java.time.Instant
 /**
 * ItineraryEntity represents a travel itinerary in the database system inside the app.
 * @property id Unique identifier for the itinerary.
 * @property title Title of the itinerary.
 * @property startDate Start date of the itinerary.
 * @property endDate End date of the itinerary.
 * @property totalItineraryPrice Total price of the itinerary.
  *
  * @author ricardoceadev
 */

data class ItineraryEntity(
     val id: String = "",
     val title: String = "",
     val startDate: Instant?,
     val endDate: Instant?,
     val totalItineraryPrice: Double = 0.0,
)
