package com.projectlab.core.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.data.model.dto.FirestoreActivityDTO
import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.FirestoreActivityRepository
import com.projectlab.core.domain.util.DataError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * FirestoreActivityRepositoryImpl is a concrete implementation of the ActivityRepository interface.
 * It uses Firebase Firestore to perform CRUD operations on activity data.
 *
 * @param firestore The FirebaseFirestore instance used to interact with Firestore.
 */

class FirestoreActivityRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirestoreActivityRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createActivity(activity: ActivityEntity): Result<EntityId> = runCatching {
        // user reference:
        var userDoc = firestore
            .collection("Users")
            .document(activity.userRef?.value ?: throw IllegalArgumentException("userRef is null"))
        // itineraryReference:
        var itinDoc = userDoc
            .collection("Itineraries")
            .document(activity.itineraryRef?.value ?: throw IllegalArgumentException("itineraryRef is null"))
        // Location reference:
        var locationDoc = firestore
            .collection("Locations")
            .document(activity.locationRef?.value ?: throw IllegalArgumentException("locationRef is null"))
        // create DTO:
        val dto = FirestoreActivityDTO.fromDomain(activity, userDoc, itinDoc, locationDoc)
        // add to firestore:
        val activityCol = itinDoc.collection("Activities")
        val docRef = activityCol.document()
        docRef.set(dto).await()
        // return id:
        EntityId(docRef.id)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getActivityById(
        userId: String,
        itinId: String,
        activityId: String
    ): Flow<ActivityEntity?> = flow {
        // Get the document reference for the activity
        val docRef = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Activities").document(activityId)
        // we get the snapshot and map it to the DTO
        val snap = docRef.get().await()
        if (snap.exists()) {
            val dto = snap.toObject(FirestoreActivityDTO::class.java)
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

    override suspend fun getAllActivitiesForItinerary(
        userId: String,
        itinId: String
    ): Flow<List<ActivityEntity>> = flow {
        val snaps = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Activities").get().await()

        val list = snaps.documents.mapNotNull { doc ->
            doc.toObject(FirestoreActivityDTO::class.java)
                ?.toDomain(
                    docId = doc.id,
                    userRef = EntityId(userId),
                    itineraryRef = EntityId(itinId)
                )
        }
        emit(list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateActivity(activity: ActivityEntity): Result<Unit> = runCatching {
        val userId = activity.userRef?.value
            ?: throw IllegalArgumentException("userRef is null")
        val itinId = activity.itineraryRef?.value
            ?: throw IllegalArgumentException("itineraryRef is null")

        val userDoc = firestore.collection("Users").document(userId)
        val itinDoc = userDoc.collection("Itineraries").document(itinId)

        val dto = FirestoreActivityDTO.fromDomain(
            domain = activity,
            userDoc = userDoc,
            itineraryDoc = itinDoc,
            locationDoc = firestore.collection("Locations")
                .document(activity.locationRef?.value ?: throw IllegalArgumentException("locationRef is null"))
        )

        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Activities").document(activity.id)
            .set(dto).await()
    }

    override suspend fun deleteActivity(
        userId: String,
        itinId: String,
        activityId: String
        ): Result<Unit> = runCatching {
            firestore.collection("Users").document(userId)
                .collection("Itineraries").document(itinId)
                .collection("Activities").document(activityId)
                .delete().await()
    }
}