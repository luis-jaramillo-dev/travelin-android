package com.projectlab.core.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.data.model.dto.FirestoreHotelDTO
import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * FirestoreHotelRepositoryImpl is a concrete implementation of the HotelRepository interface.
 * It uses Firebase Firestore to perform CRUD operations on hotel data.
 *
 * @param firestore The FirebaseFirestore instance used to interact with Firestore.
 */

class FirestoreHotelRepositoryImpl @Inject constructor (
    private val firestore : FirebaseFirestore
) : HotelRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createHotel(hotel: HotelEntity): Result<EntityId> = runCatching {
        // user reference:
        var userDoc = firestore
            .collection("Users")
            .document(hotel.userRef?.value ?: throw IllegalArgumentException("userRef is null"))
        // itinerary reference:
        var itinDoc = userDoc
            .collection("Itineraries")
            .document(hotel.itineraryRef?.value ?: throw IllegalArgumentException("itineraryRef is null"))
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
        userId: String,
        itinId: String,
        hotelId: String
    ): Flow<HotelEntity?> = flow {
        // Get the document reference for the hotel
        val docRef = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Hotels").document(hotelId)
        // we get the snapshot and map it to the DTO
        val snap = docRef.get().await()
        if (snap.exists()) {
            val dto = snap.toObject(FirestoreHotelDTO::class.java)
            emit(
                dto?.toDomain(
                    snap.id,
                    EntityId(userId),
                    EntityId(itinId)
                )
            )
        } else {
            emit(null)
        }
    }

    override suspend fun getAllHotelsForItinerary(
        userId: String,
        itinId: String
    ): Flow<List<HotelEntity>> = flow {
        val snaps = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Hotels").get().await()

        // For each document, convert the DTO to Domain:
        val list = snaps.documents.mapNotNull { doc ->
            doc.toObject(FirestoreHotelDTO::class.java)
                ?.toDomain(
                    doc.id,
                    EntityId(userId),
                    EntityId(itinId)
                )
        }
        emit(list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateHotel(hotel: HotelEntity): Result<Unit> = runCatching {
        val userId = hotel.userRef?.value
            ?: throw IllegalArgumentException("userRef is null")
        val itinId = hotel.itineraryRef?.value
            ?: throw IllegalArgumentException("itineraryRef is null")

        val userDoc = firestore.collection("Users").document(userId)
        val itinDoc = userDoc.collection("Itineraries").document(itinId)

        val dto = FirestoreHotelDTO.fromDomain(
            domain = hotel,
            userDocRef = userDoc,
            itineraryDocRef = itinDoc,
            locationDocRef = firestore.collection("Locations")
                .document(hotel.locationRef?.value ?: throw IllegalArgumentException("locationRef is null"))
        )

        // We do .set() on the existing document and await. TODO: handle errors.
        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Hotels").document(hotel.id)
            .set(dto).await()
    }

    override suspend fun deleteHotel(
        userId: String,
        itinId: String,
        hotelId: String
    ): Result<Unit> = runCatching {
        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Hotels").document(hotelId)
            .delete().await()
    }


}