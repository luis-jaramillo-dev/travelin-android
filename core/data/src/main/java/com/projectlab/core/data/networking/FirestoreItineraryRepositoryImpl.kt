package com.projectlab.core.data.networking

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.repository.ItineraryRepository
import com.projectlab.core.data.model.dto.FirestoreItineraryDTO
import kotlinx.coroutines.flow.Flow
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
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createItinerary(itinerary: ItineraryEntity): Result<EntityId> = runCatching {
        // user reference:
        val userDocRef = firestore
            .collection("Users")
            .document(itinerary.userRef?.value ?: throw IllegalArgumentException("userRef is null"))
        // create dto:
        val dto = FirestoreItineraryDTO.fromDomain(itinerary)
        // add to firestore:
        val itCol = userDocRef.collection("Itineraries")
        val docRef = itCol.document()
        docRef.set(dto).await()
        // return id:
        EntityId(docRef.id)
    }

    override suspend fun getItinerariesById(id: String): Flow<ItineraryEntity?> {
        TODO("Not yet implemented")
    }


}