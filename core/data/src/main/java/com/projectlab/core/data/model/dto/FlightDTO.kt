package com.projectlab.core.data.model.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.entity.FlightEntity
import java.time.Instant
import java.util.Date

// Assuming you have a DocumentReference for the airport collection
val documentAirportId = "your_airport_document_id" // Replace with actual document ID
val docRefAirport = FirebaseFirestore.getInstance().collection("airport").document(documentAirportId)
// TODO: Convert FlightDTO in data class
class FlightDTO @RequiresApi(Build.VERSION_CODES.O) constructor(
    val airline: String = "",
    val flightNumber: String = "",
    val flightClass: String = "",
    val departureAirport: Map<String, Any> = mapOf(
        "airportCodeRef" to docRefAirport,
        "time" to Timestamp(Date(System.currentTimeMillis()))
    ),
    val arrivalAirport: Map<String, Any> = mapOf(
        "airportCodeRef" to docRefAirport,
        "time" to Timestamp(Date(System.currentTimeMillis()))
    ),
    val passengerNumber: Map<String, Any> = mapOf(
        "adultsNumber" to 0,
        "kidsNumber" to 0,
        "babiesWithSitNumber" to 0,
        "babiesInArmsNumber" to 0
    ),
    val price: Double = 0.0,
    val userRef: DocumentReference? = null,
    val itineraryRef: DocumentReference? = null
){
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromDomain(domain: FlightEntity,
                       userDocRef: DocumentReference,
                       itineraryDocRef: DocumentReference
        ): FlightDTO =
            FlightDTO(
                airline = domain.airline,
                flightNumber = domain.flightNumber,
                flightClass = domain.flightClass,
                departureAirport = domain.departureAirport,
                arrivalAirport = domain.arrivalAirport,
                passengerNumber = domain.passengerNumber,
                price = domain.price,
                userRef = userDocRef,
                itineraryRef = itineraryDocRef
            )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toDomain(docId: String): FlightEntity =
        FlightEntity(
            id = EntityId(docId),
            airline = airline,
            flightNumber = flightNumber,
            flightClass = flightClass,
            departureAirport = departureAirport,
            arrivalAirport = arrivalAirport,
            passengerNumber = passengerNumber,
            price = price,
            userRef = EntityId(userRef!!.id), // Assuming userRef is not null
            itineraryRef = EntityId(itineraryRef!!.id) // Assuming itineraryRef is not null
        )
}