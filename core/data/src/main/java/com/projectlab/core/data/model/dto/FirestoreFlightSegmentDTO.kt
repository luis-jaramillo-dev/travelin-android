package com.projectlab.core.data.model.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.Timestamp
import com.projectlab.core.domain.entity.FlightSegmentEntity
import com.projectlab.core.domain.model.EntityId
import java.time.Instant
import java.util.Date

data class FirestoreFlightSegmentDTO (
    val departureAirportCodeRef : DocumentReference? = null,
    val arrivalAirportCodeRef : DocumentReference? = null,
    val departureTime : Timestamp = Timestamp.now(),
    val arrivalTime : Timestamp = Timestamp.now(),
    val requiresPlaneChange : Boolean = false,
    val connectionInfo : Map<String, Any> = mapOf(
        "nextAirline" to "",
        "nextFlightNumber" to "",
        "connectionGate" to ""
    )
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromDomain(
            domain: FlightSegmentEntity,
            userDocRef: DocumentReference,
            itineraryDocRef: DocumentReference,
            flightDocRef: DocumentReference,
            airportDepartureDocRef: DocumentReference,
            airportArrivalDocRef: DocumentReference
        ): FirestoreFlightSegmentDTO =
            FirestoreFlightSegmentDTO(
                departureAirportCodeRef = airportDepartureDocRef,
                arrivalAirportCodeRef = airportArrivalDocRef,
                departureTime = Timestamp(Date.from(domain.departureTime)),
                arrivalTime = Timestamp(Date.from(domain.arrivalTime)),
                requiresPlaneChange = domain.requiresPlaneChange,
                connectionInfo = domain.connectionInfo
            )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toDomain(
        docId: String,
        userRef: EntityId,
        itineraryRef: EntityId,
        flightRef: EntityId
    ): FlightSegmentEntity = FlightSegmentEntity(
        id = EntityId(docId),
        departureAirportCodeRef = departureAirportCodeRef?.let { EntityId(it.id) },
        arrivalAirportCodeRef = arrivalAirportCodeRef?.let { EntityId(it.id) },
        departureTime = Instant.ofEpochMilli(departureTime.toDate().time),
        arrivalTime = Instant.ofEpochMilli(arrivalTime.toDate().time),
        requiresPlaneChange = requiresPlaneChange,
        connectionInfo = connectionInfo,
        userRef = userRef,
        itineraryRef = itineraryRef,
        flightRef = flightRef
    )
}