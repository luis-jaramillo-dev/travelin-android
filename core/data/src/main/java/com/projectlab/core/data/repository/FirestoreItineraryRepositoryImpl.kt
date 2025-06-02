package com.projectlab.core.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.repository.ItineraryRepository
import com.projectlab.core.data.model.dto.FirestoreItineraryDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * FirestoreItineraryRepositoryImpl is a concrete implementation of the ItineraryRepository interface.
 * It uses Firebase Firestore to perform CRUD operations on itinerary data.
 *
 * @param firestore The FirebaseFirestore instance used to interact with Firestore.
 */

class FirestoreItineraryRepositoryImpl @Inject constructor (
    private val firestore: FirebaseFirestore
) : ItineraryRepository {
    private val usersCol = firestore.collection("Users")

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createItinerary(itinerary: ItineraryEntity): Result<EntityId> = runCatching {
        // user reference:
        var userDocRef = usersCol.document(itinerary.userRef?.value
            ?: throw IllegalArgumentException("userRef is null"))
        // create dto:
        val dto = FirestoreItineraryDTO.fromDomain(itinerary)
        // add to firestore:
        val itCol = userDocRef.collection("Itineraries")
        val docRef = itCol.document()
        docRef.set(dto).await()
        // return id:
        EntityId(docRef.id)
    }

    override suspend fun getItinerariesById(
        userId: String,
        itineraryId: String
    ): Flow<ItineraryEntity?> = flow {
        // we get the snapshot and map it to the DTO
        // If the snapshot exists, we map to domain, convert it to a ItineraryEntity and emit
        // Otherwise, we emit null
        val snap = usersCol.document(userId)
            .collection("Itineraries").document(itineraryId)
            .get().await()
        if (snap.exists()) {
            val dto = snap.toObject(FirestoreItineraryDTO::class.java)
            emit(dto?.toDomain(
                docId = snap.id,
                userRef =  EntityId(userId))
            )
        } else {
            emit(null)
        }
    }

    override suspend fun getAllItinerariesForUser(userId: String): Flow<List<ItineraryEntity>> = flow {
        // Fetch all itineraries for a user
        // Route to the itineraries collection of the user
        val snaps = usersCol.document(userId)
            .collection("Itineraries").get().await()

        //For each document, convert the DTO to a domain (ItineraryEntity):
        val list = snaps.documents.mapNotNull { doc ->
            doc.toObject(FirestoreItineraryDTO::class.java)
                ?.toDomain(
                    docId =  doc.id,
                    userRef = EntityId(userId)
                )
        }
        emit(list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateItinerary(itinerary: ItineraryEntity): Result<Unit> = runCatching {
        // We retrieve the userId from the object reference
        val userId = itinerary.userRef?.value
            ?: throw IllegalArgumentException("userRef is null")

        // Reconstruct the document routes/references:
        val userDocRef = usersCol.document(userId) // we don't used it still

        // convert to dto:
        val dto = FirestoreItineraryDTO.fromDomain(
            domain = itinerary
        )

        // Overwrite, set() the specific itinerary document. TODO: handle errors.
        usersCol.document(userId)
            .collection("Itineraries").document(itinerary.id)
            .set(dto).await()
    }

    override suspend fun deleteItinerary(
        userId: String,
        itineraryId: String
    ): Result<Unit> = runCatching {
        usersCol.document(userId)
            .collection("Itineraries").document(itineraryId)
            .delete().await()
    }
}