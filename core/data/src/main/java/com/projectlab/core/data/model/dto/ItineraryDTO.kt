package com.projectlab.core.data.model.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.entity.ItineraryEntity
import java.time.Instant

data class ItineraryDTO (

    val title: String = "",
    val startDate: Timestamp = Timestamp.now(),
    val endDate: Timestamp = Timestamp.now(),
    val totalItineraryPrice: Double = 0.0,
    val userRef: DocumentReference? = null
){
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromDomain(domain : ItineraryEntity, userDocRef : DocumentReference): ItineraryDTO =
            ItineraryDTO(
                title = domain.title,
                startDate = Timestamp(domain.startDate.toEpochMilli() /1000, 0),
                endDate = Timestamp(domain.endDate.toEpochMilli() /1000, 0),
                totalItineraryPrice = domain.totalItineraryPrice,
                userRef = userDocRef
            )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toDomain(docId : String): ItineraryEntity =
        ItineraryEntity(
            id = EntityId(docId),
            title = title,
            startDate = Instant.ofEpochSecond(startDate.seconds * 1_000),
            endDate = Instant.ofEpochSecond(endDate.seconds * 1_000),
            totalItineraryPrice = totalItineraryPrice,
            userRef = EntityId(userRef!!.id) // Assuming userRef is not null
        )

}