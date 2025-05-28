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
import kotlinx.coroutines.tasks.await

/**
 * FirestoreFlightSegmentRepositoryImpl is a concrete implementation of the FlightSegmentRepository interface.
 * It uses Firebase Firestore to perform CRUD operations on flight segment data.
 *
 * @param firestore The FirebaseFirestore instance used to interact with Firestore.
 */

class FirestoreFlightSegmentRepositoryImpl @Inject constructor (
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

    override suspend fun getFlightSegmentById(id: String): Flow<FlightSegmentEntity?> {
        TODO("Not yet implemented")
    }
}