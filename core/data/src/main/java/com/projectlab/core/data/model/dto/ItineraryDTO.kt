package com.projectlab.core.data.model.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.entity.ItineraryEntity
import java.time.Instant
import java.util.Date

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
                startDate = Timestamp(Date.from(domain.startDate)),
                endDate = Timestamp(Date.from(domain.endDate)),
                totalItineraryPrice = domain.totalItineraryPrice,
                userRef = userDocRef
            )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toDomain(docId : String): ItineraryEntity =
        ItineraryEntity(
            id = EntityId(docId),
            title = title,
            startDate = Instant.ofEpochMilli(startDate.toDate().time),
            endDate = Instant.ofEpochMilli(endDate.toDate().time),
            totalItineraryPrice = totalItineraryPrice,
            userRef = EntityId(userRef!!.id) // Assuming userRef is not null
        )

}