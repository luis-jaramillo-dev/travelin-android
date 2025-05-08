package com.projectlab.core.data.networking

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.repository.ItineraryRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreItineraryRepositoryImpl @Inject constructor (
    private val firestore: FirebaseFirestore
) : ItineraryRepository {

    private val itinerariesCol = firestore.collection("itineraries")

    override suspend fun createItinerary(itinerary: ItineraryEntity) = runCatching {
        val itinerariesCol = firestore
            .collection("Users")
            .document(itinerary.userRef!!.id)
            .collection("Itineraries")
        val docRef = itinerariesCol.document()
        docRef.set(itinerary).await()
        docRef.id
    }

    override suspend fun getItineraries(userRef: DocumentReference) = runCatching {
        val snapshot = userRef
            .collection("Itineraries")
            .get()
            .await()
        snapshot.toObjects(ItineraryEntity::class.java)
    }
}