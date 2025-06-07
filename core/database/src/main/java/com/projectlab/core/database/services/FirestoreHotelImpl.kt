package com.projectlab.core.database.services

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.database.dto.FirestoreHotelDTO
import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.UserSessionProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * FirestoreHotelImpl is a concrete implementation of the FirestoreHotel interface.
 * It uses Firebase Firestore to perform CRUD operations on hotel data.
 *
 * @param firestore The FirebaseFirestore instance used to interact with Firestore.
 */

class FirestoreHotelImpl @Inject constructor (
    private val firestore : FirebaseFirestore,
    private val userSessionProvider: UserSessionProvider,
) : FirestoreHotel {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createHotel(
        itinId: String,
        hotel: HotelEntity
    ): Result<EntityId> = runCatching {
        // Get the user ID from the session provider
        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")
        // user reference:
        var userDoc = firestore
            .collection("Users")
            .document(userId)
        // itinerary reference:
        var itinDoc = userDoc
            .collection("Itineraries")
            .document(itinId)
        // Location reference:
        var locationDoc = firestore
            .collection("Locations")
            .document(hotel.locationRef?.value ?: throw IllegalArgumentException("locationRef is null"))
        // create dto:
        val dto = FirestoreHotelDTO.fromDomain(hotel, userDoc, itinDoc, locationDoc)
        // add to firestore:
        val hotelCol = itinDoc.collection("Hotels")
        val docRef  = hotelCol.document()
        docRef.set(dto).await()
        // return id:
        EntityId(docRef.id)
    }

    override suspend fun getHotelsById(
        itinId: String,
        hotelId: String
    ): Result<HotelEntity?> = runCatching {
        // Get the user ID from the session provider
        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")
        // Get the document reference for the hotel
        val docRef = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Hotels").document(hotelId)
        // we get the snapshot and map it to the DTO
        // If the snapshot exists, we convert it to a HotelEntity
        // Otherwise, we emit null
        val snap = docRef.get().await()
        if (snap.exists()) {
            val dto = snap.toObject(FirestoreHotelDTO::class.java)
            (
                dto?.toDomain(
                    snap.id,
                    EntityId(userId),
                    EntityId(itinId)
                )
            )
        } else {
            (null)
        }
    }

    override suspend fun getAllHotelsForItinerary(
        itinId: String
    ): Result<List<HotelEntity>> = runCatching {
        // Get the user ID from the session provider
        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")
        // Route to the Hotels collection for the given user and itinerary
        val snaps = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Hotels").get().await()

        // For each document, convert the DTO to Domain (HotelEntity):
        val list = snaps.documents.mapNotNull { doc ->
            doc.toObject(FirestoreHotelDTO::class.java)
                ?.toDomain(
                    doc.id,
                    EntityId(userId),
                    EntityId(itinId)
                )
        }
        (list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateHotel(
        itinId: String,
        hotel: HotelEntity
    ): Result<Unit> = runCatching {
        // Get the user ID from the session provider
        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")
        // Reconstruct the document routes/references:
        val userDoc = firestore.collection("Users").document(userId)
        val itinDoc = userDoc.collection("Itineraries").document(itinId)

        // Convert to DTO:
        val dto = FirestoreHotelDTO.fromDomain(
            domain = hotel,
            userDocRef = userDoc,
            itineraryDocRef = itinDoc,
            locationDocRef = firestore.collection("Locations")
                .document(hotel.locationRef?.value ?: throw IllegalArgumentException("locationRef is null"))
        )

        // Overwrite, set() the specific hotel document. TODO: handle errors.
//        firestore.collection("Users").document(userId)
//            .collection("Itineraries").document(itinId)
        itinDoc.collection("Hotels").document(hotel.id)
            .set(dto).await()
    }

    override suspend fun deleteHotel(
        itinId: String,
        hotelId: String
    ): Result<Unit> = runCatching {
        // Get the user ID from the session provider
        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")
        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Hotels").document(hotelId)
            .delete().await()
    }


}