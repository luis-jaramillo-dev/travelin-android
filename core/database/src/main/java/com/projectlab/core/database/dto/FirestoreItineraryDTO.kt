package com.projectlab.core.database.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.entity.ItineraryEntity
import java.time.Instant
import java.util.Date

/**
 * Data Transfer Object (DTO) for Firestore Itinerary.
 *
 * @property title The title of the itinerary.
 * @property startDate The start date of the itinerary.
 * @property endDate The end date of the itinerary.
 * @property totalItineraryPrice The total price of the itinerary.
 *
 * @author ricardoceadev
 */

data class FirestoreItineraryDTO (

    val title: String = "",
    val startDate: Timestamp = Timestamp.now(),
    val endDate: Timestamp = Timestamp.now(),
    val totalItineraryPrice: Double = 0.0,
    // val userRef: DocumentReference? = null
){
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromDomain(domain : ItineraryEntity): FirestoreItineraryDTO =
            FirestoreItineraryDTO(
                title = domain.title,
                startDate = Timestamp(Date.from(domain.startDate)),
                endDate = Timestamp(Date.from(domain.endDate)),
                totalItineraryPrice = domain.totalItineraryPrice,
                // userRef = userDocRef
            )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toDomain(docId : String, userRef : EntityId): ItineraryEntity =
        ItineraryEntity(
            // TODO: check if we need to use EntityId here (and in others DTOs) instead of String
            // id = EntityId(docId),
            id = docId,
            title = title,
            startDate = Instant.ofEpochMilli(startDate.toDate().time),
            endDate = Instant.ofEpochMilli(endDate.toDate().time),
            totalItineraryPrice = totalItineraryPrice,
            userRef = userRef
        )

}