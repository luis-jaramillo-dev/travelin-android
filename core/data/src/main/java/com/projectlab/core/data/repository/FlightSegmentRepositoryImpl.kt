package com.projectlab.core.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.data.model.dto.FirestoreFlightSegmentDTO
import com.projectlab.core.domain.entity.FlightSegmentEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.FlightSegmentRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

/**
 * FirestoreFlightSegmentRepositoryImpl is a concrete implementation of the FlightSegmentRepository interface.
 * It uses Firebase Firestore to perform CRUD operations on flight segment data.
 *
 * @param firestore The FirebaseFirestore instance used to interact with Firestore.
 */

class FlightSegmentRepositoryImpl @Inject constructor (
    private val firestore: FirebaseFirestore
) : FlightSegmentRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createFlightSegment(flightSegment: FlightSegmentEntity
    ): Result<EntityId> = runCatching {
        // user reference:
        var userDocRef = firestore
            .collection("Users")
            .document(flightSegment.userRef?.value ?:
            throw IllegalArgumentException("userRef is null"))
        // itinerary reference:
        var itineraryDocRef = userDocRef
            .collection("Itineraries")
            .document(flightSegment.itineraryRef?.value ?:
            throw IllegalArgumentException("itineraryRef is null"))
        // flight reference:
        var flightDocRef = itineraryDocRef
            .collection("Flights")
            .document(flightSegment.flightRef?.value ?:
            throw IllegalArgumentException("flightRef is null"))
        // departure airport reference:
        var airportDepartureDocRef = firestore
            .collection("Airports")
            .document(flightSegment.departureAirportCodeRef?.toString() ?:
            throw IllegalArgumentException("airportDepartureCodeRef is null"))
        // arrival airport reference:
        var airportArrivalDocRef = firestore
            .collection("Airports")
            .document(flightSegment.departureAirportCodeRef?.toString() ?:
            throw IllegalArgumentException("airportArrivalCodeRef is null"))
        // create dto:
        val dto = FirestoreFlightSegmentDTO.fromDomain(
            flightSegment,
            userDocRef,
            itineraryDocRef,
            flightDocRef,
            airportDepartureDocRef,
            airportArrivalDocRef
        )
        // add to firestore:
        val flightSegmentCol = flightDocRef.collection("FlightSegments")
        val docRef = flightSegmentCol.document()
        docRef.set(dto).await()
        // return id:
        EntityId(docRef.id)
    }

    override suspend fun getFlightSegmentById(
        userId: String,
        itinId: String,
        flightId: String,
        segmentId: String
    ): Flow<FlightSegmentEntity?> = flow {
        // Get the document reference for the flight segment
        val docRef = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Flights").document(flightId)
            .collection("FlightSegments").document(segmentId)

        // we get the snapshot and map it to the DTO
        // If the snapshot exists, we map to domain, convert it to a FlightSegmentEntity and emit
        // Otherwise, we emit null
        val snap = docRef.get().await()
        if (snap.exists()) {
            val dto = snap.toObject(FirestoreFlightSegmentDTO::class.java)
            emit(
                dto?.toDomain(
                    docId = snap.id,
                    userRef = EntityId(userId),
                    itineraryRef = EntityId(itinId),
                    flightRef = EntityId(flightId)
                )
            )
        } else {
            emit(null)
        }
    }

    override suspend fun getAllFlightSegmentsForFlight(
        userId: String,
        itinId: String,
        flightId: String
    ): Flow<List<FlightSegmentEntity>> = flow {
        // Route to the FlightSegments collection for the given user, itinerary and flight
        val snaps = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Flights").document(flightId)
            .collection("FlightSegments").get().await()

        // For each document, convert the DTO to domain (FlightSegmentEntity):
        val list = snaps.documents.mapNotNull { doc ->
            doc.toObject(FirestoreFlightSegmentDTO::class.java)
                ?.toDomain(
                    docId = doc.id,
                    userRef = EntityId(userId),
                    itineraryRef = EntityId(itinId),
                    flightRef = EntityId(flightId)
                )
        }
        emit(list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateFlightSegment(
        flightSegment: FlightSegmentEntity
    ): Result<Unit> = runCatching {
        // We retrieve the userId, itineraryId and flightId from the object reference
        val userId = flightSegment.userRef?.value
            ?: throw IllegalArgumentException("userRef is null")
        val itineraryId = flightSegment.itineraryRef?.value
            ?: throw IllegalArgumentException("itineraryRef is null")
        val flightId = flightSegment.flightRef?.value
            ?: throw IllegalArgumentException("flightRef is null")

        // Reconstruct the document routes/references:
        val userDocRef = firestore.collection("Users").document(userId)
        val itineraryDocRef = userDocRef.collection("Itineraries").document(itineraryId)
        val flightDocRef = itineraryDocRef.collection("Flights").document(flightId)

        // Convert to DTO:
        val dto = FirestoreFlightSegmentDTO.fromDomain(
            domain = flightSegment,
            userDocRef = userDocRef,
            itineraryDocRef = itineraryDocRef,
            flightDocRef = flightDocRef,
            airportDepartureDocRef = firestore.collection("Airports")
                .document(flightSegment.departureAirportCodeRef?.value
                    ?: throw IllegalArgumentException("departureAirportCodeRef is null")),
            airportArrivalDocRef = firestore.collection("Airports")
                .document(flightSegment.arrivalAirportCodeRef?.value
                    ?: throw IllegalArgumentException("arrivalAirportCodeRef is null"))
        )

        // Overwrite, set() the specific flight segment document. TODO: handle errors.
        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itineraryId)
            .collection("Flights").document(flightId)
            .collection("FlightSegments").document(flightSegment.id)
            .set(dto).await()
    }

    override suspend fun deleteFlightSegment(
        userId: String,
        itinId: String,
        flightId: String,
        segmentId: String
    ): Result<Unit> = runCatching {
        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Flights").document(flightId)
            .collection("FlightSegments").document(segmentId)
            .delete().await()
    }
}