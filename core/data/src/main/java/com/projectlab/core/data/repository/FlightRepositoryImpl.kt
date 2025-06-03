package com.projectlab.core.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.data.model.dto.FirestoreFlightDTO
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.entity.FlightEntity
import com.projectlab.core.domain.repository.FlightRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * FlightRepositoryImpl is a concrete implementation of the FlightRepository interface.
 * It uses Firestore to perform CRUD operations on flight data.
 *
 * @param firestore The FirebaseFirestore instance used to interact with Firestore.
 */

class FlightRepositoryImpl @Inject constructor (
    private val firestore: FirebaseFirestore
) : FlightRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createFlight(flight: FlightEntity): Result<EntityId> = runCatching {
        // user reference:
        var userDocRef = firestore
            .collection("Users")
            .document(flight.userRef?.value ?: throw IllegalArgumentException("userRef is null"))
        // itinerary reference:
        var itineraryDocRef = userDocRef
            .collection("Itineraries")
            .document(flight.itineraryRef?.value ?: throw IllegalArgumentException("itineraryRef is null"))
        // departure airport reference:
        var airportDepartureDocRef = firestore
            .collection("Airports")
            .document(flight.departureAirport["airportCodeRef"]?.toString() ?:
            throw IllegalArgumentException("airportDepartureCodeRef is null"))
        // departure airport reference:
        var airportArrivalDocRef = firestore
            .collection("Airports")
            .document(flight.departureAirport["airportCodeRef"]?.toString() ?:
            throw IllegalArgumentException("airportArrivalCodeRef is null"))
        // create dto:
        val dto = FirestoreFlightDTO.fromDomain(
            flight,
            userDocRef,
            itineraryDocRef,
            airportDepartureDocRef,
            airportArrivalDocRef
        )
        // add to firestore:
        val flightCol = itineraryDocRef.collection("Flights")
        val docRef = flightCol.document()
        docRef.set(dto).await()
        // return id:
        EntityId(docRef.id)

    }

    override suspend fun getFlightById(
        userId: String,
        itinId: String,
        flightId: String
    ): Flow<FlightEntity?> = flow {
        // Get the document reference for the flight
        val docRef = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Flights").document(flightId)
        // we get the snapshot and map it to the DTO
        // If the snapshot exists, we map to domain, convert it to a FlightEntity and emit
        // Otherwise, we emit null
        val snap = docRef.get().await()
        if (snap.exists()) {
            val dto = snap.toObject(FirestoreFlightDTO::class.java)
            emit(
                dto?.toDomain(
                    docId = snap.id,
                    userRef = EntityId(userId),
                    itineraryRef = EntityId(itinId)
                )
            )
        } else {
            emit(null)
        }
    }

    override suspend fun getAllFlightsForItinerary(
        userId: String,
        itinId: String
    ): Flow<List<FlightEntity>> = flow {
        // route to the Flights collection for the given user and itinerary
        val snaps = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Flights").get().await()

        // For each document, convert the DTO to Domain (FlightEntity):
        val list = snaps.documents.mapNotNull { doc ->
            doc.toObject(FirestoreFlightDTO::class.java)
                ?.toDomain(
                    docId = doc.id,
                    userRef = EntityId(userId),
                    itineraryRef = EntityId(itinId)
                )
        }
        emit(list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateFlight(flight: FlightEntity): Result<Unit> = runCatching {
        // We retrieve the userId and itineraryId from the object reference
        val userId = flight.userRef?.value
            ?: throw IllegalArgumentException("userRef is null")
        val itineraryId = flight.itineraryRef?.value
            ?: throw IllegalArgumentException("itineraryRef is null")

        // Reconstruct the document routes:
        val userDocRef = firestore.collection("Users").document(userId)
        val itineraryDocRef = userDocRef.collection("Itineraries").document(itineraryId)

        // Convert to DTO:
        val dto = FirestoreFlightDTO.fromDomain(
            domain = flight,
            userDocRef = userDocRef,
            itineraryDocRef = itineraryDocRef,
            airportDepartureDocRef = firestore.collection("Airports")
                .document(flight.departureAirport["airportCodeRef"]?.toString()
                    ?: throw IllegalArgumentException("departureAirport.airportCodeRef is null")),
            airportArrivalDocRef = firestore.collection("Airports")
                .document(flight.arrivalAirport["airportCodeRef"]?.toString()
                    ?: throw IllegalArgumentException("arrivalAirport.airportCodeRef is null"))
        )

        // Overwrite, set() the specific flight document. TODO: handle errors.
        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itineraryId)
            .collection("Flights").document(flight.id)
            .set(dto).await()

    }

    override suspend fun deleteFlight(
        userId: String,
        itinId: String,
        flightId: String
    ): Result<Unit> = runCatching {
        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Flights").document(flightId)
            .delete().await()
    }
}