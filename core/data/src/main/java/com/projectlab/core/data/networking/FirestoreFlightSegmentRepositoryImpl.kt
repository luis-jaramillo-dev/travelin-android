package com.projectlab.core.data.networking

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

class FirestoreFlightSegmentRepositoryImpl @Inject constructor (
    private val firestore: FirebaseFirestore
) : FlightSegmentRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createFlightSegment(flightSegment: FlightSegmentEntity
    ): Result<EntityId> = runCatching {
        // user reference:
        val userDocRef = firestore
            .collection("users")
            .document(flightSegment.userRef?.value ?:
            throw IllegalArgumentException("userRef is null"))
        // itinerary reference:
        val itineraryDocRef = userDocRef
            .collection("itineraries")
            .document(flightSegment.itineraryRef?.value ?:
            throw IllegalArgumentException("itineraryRef is null"))
        // flight reference:
        val flightDocRef = itineraryDocRef
            .collection("flights")
            .document(flightSegment.flightRef?.value ?:
            throw IllegalArgumentException("flightRef is null"))
        // airport reference:
        val airportDocRef = firestore
            .collection("airports")
            .document(flightSegment.departureAirportCodeRef?.toString() ?:
            throw IllegalArgumentException("airportCodeRef is null"))
        // create dto:
        val dto = FirestoreFlightSegmentDTO.fromDomain(
            flightSegment,
            userDocRef,
            itineraryDocRef,
            flightDocRef,
            airportDocRef
        )
        // add to firestore:
        val flightSegmentCol = flightDocRef.collection("flightSegments")
        val docRef = flightSegmentCol.document()
        docRef.set(dto).await()
        // return id:
        EntityId(docRef.id)
    }

    override suspend fun getFlightSegmentById(id: String): Flow<FlightSegmentEntity?> {
        TODO("Not yet implemented")
    }
}