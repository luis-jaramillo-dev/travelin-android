package com.projectlab.core.data.model.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.model.EntityId
import java.time.Instant
import java.util.Date

data class FirestoreActivityDTO (
    val name: String = "",
    val locationRef: DocumentReference? = null,
    val activityDate: Timestamp = Timestamp.now(),
    val details: String = "",
    val activityPrice: Double = 0.0
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromDomain(
            domain: ActivityEntity,
            userDoc: DocumentReference,
            itineraryDoc: DocumentReference,
            locationDoc: DocumentReference
        ): FirestoreActivityDTO = FirestoreActivityDTO(
            name          = domain.name,
            locationRef   = locationDoc,
            activityDate  = Timestamp(Date.from(domain.activityDate)),
            details       = domain.details,
            activityPrice = domain.activityPrice
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toDomain(
        docId: String,
        userRef: EntityId,
        itineraryRef: EntityId
    ): ActivityEntity = ActivityEntity(
        id            = docId,
        name          = name,
        locationRef   = locationRef?.let { EntityId(it.id) },
        activityDate  = Instant.ofEpochMilli(activityDate.toDate().time),
        details       = details,
        activityPrice = activityPrice,
        userRef       = userRef,
        itineraryRef  = itineraryRef
    )
}