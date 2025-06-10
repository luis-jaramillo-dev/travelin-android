package com.projectlab.core.database.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.entity.FlightEntity
import java.util.Date

/**
 * Data Transfer Object (DTO) for Firestore Flight.
 *
 * @property airline The airline of the flight.
 * @property flightNumber The flight number.
 * @property flightClass The class of the flight.
 * @property departureAirport A map containing the airport code reference and time of departure.
 * @property arrivalAirport A map containing the airport code reference and time of arrival.
 * @property passengerNumber A map containing the number of adults, kids, babies with sit,
 * and babies in arms.
 * @property price The price of the flight.
 *
 * @author ricardoceadev
 */

data class FirestoreFlightDTO @RequiresApi(Build.VERSION_CODES.O) constructor(
    val airline: String = "",
    val flightNumber: String = "",
    val flightClass: String = "",
    val departureAirport: Map<String, Any> = mapOf(
        "airportCodeRef" to "",
        "time" to Timestamp(Date(System.currentTimeMillis()))
    ),
    val arrivalAirport: Map<String, Any> = mapOf(
        "airportCodeRef" to "",
        "time" to Timestamp(Date(System.currentTimeMillis()))
    ),
    val passengerNumber: Map<String, Any> = mapOf(
        "adultsNumber" to 0,
        "kidsNumber" to 0,
        "babiesWithSitNumber" to 0,
        "babiesInArmsNumber" to 0
    ),
    val price: Double = 0.0,
){
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromDomain(domain: FlightEntity,
                       userDocRef: DocumentReference,
                       itineraryDocRef: DocumentReference,
                       airportDepartureDocRef: DocumentReference,
                       airportArrivalDocRef : DocumentReference
        ): FirestoreFlightDTO =
            FirestoreFlightDTO(
                airline = domain.airline,
                flightNumber = domain.flightNumber,
                flightClass = domain.flightClass,
                departureAirport = domain.departureAirport,
                arrivalAirport = domain.arrivalAirport,
                passengerNumber = domain.passengerNumber,
                price = domain.price,
            )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toDomain(
        docId: String,
        userRef: EntityId,
        itineraryRef: EntityId
    ): FlightEntity =
        FlightEntity(
            id = docId,
            airline = airline,
            flightNumber = flightNumber,
            flightClass = flightClass,
            departureAirport = departureAirport,
            arrivalAirport = arrivalAirport,
            passengerNumber = passengerNumber,
            price = price,
        )
}