package com.projectlab.core.data.networking

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

class FirestoreFlightRepositoryImpl @Inject constructor (
    private val firestore: FirebaseFirestore
) : FlightRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createFlight(flight: FlightEntity): Result<EntityId> = runCatching {
        // user reference:
        val userDocRef = firestore
            .collection("users")
            .document(flight.userRef?.value ?: throw IllegalArgumentException("userRef is null"))
        // itinerary reference:
        val itineraryDocRef = userDocRef
            .collection("itineraries")
            .document(flight.itineraryRef?.value ?: throw IllegalArgumentException("itineraryRef is null"))
        // airport reference:
        val airportDocRef = firestore
            .collection("airports")
            .document(flight.departureAirport["airportCodeRef"]?.toString() ?: throw IllegalArgumentException("airportCodeRef is null"))
        // create dto:
        val dto = FirestoreFlightDTO.fromDomain(flight, userDocRef, itineraryDocRef, airportDocRef)
        // add to firestore:
        val flightCol = itineraryDocRef.collection("flights")
        val docRef = flightCol.document()
        docRef.set(dto).await()
        // return id:
        EntityId(docRef.id)

    }

    override suspend fun getFlightsById(id: String): Flow<FlightEntity?> {
        TODO("Not yet implemented")
    }


}