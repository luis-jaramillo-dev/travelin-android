package com.projectlab.core.database.services

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.database.dto.FirestoreActivityDTO
import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.UserSessionProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * FirestoreActivityImpl is a concrete implementation of the FirestoreActivity interface.
 * It provides methods to interact with activities in Firestore and external APIs.
 *
 * @param firestore The FirebaseFirestore instance used to interact with Firestore.
 * @param userSessionProvider The UserSessionProvider instance
 * used to get the current user's session ID.
 *
 * @author ricardoceadev
 */

class FirestoreActivityImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val userSessionProvider: UserSessionProvider,
) : FirestoreActivity {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createActivity(
        
        itinId: String,
        activity: ActivityEntity
    ): Result<EntityId> =
        runCatching {

            val userId = userSessionProvider.getUserSessionId()
                ?: throw NullPointerException("userId is null")

            var userDoc = firestore
                .collection("Users")
                .document(userId)

            var itineraryDoc = userDoc
                .collection("Itineraries")
                .document(itinId)

            val dto = FirestoreActivityDTO.fromDomain(
                domain = activity,
                userDoc = userDoc,
                itineraryDoc = itineraryDoc,
            )
            val activityCol = itineraryDoc.collection("Activities")
            val docRef = activityCol.document()
            docRef.set(dto).await()

            EntityId(docRef.id)
        }


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getActivityById(
        
        itinId: String,
        activityId: String
    ): Result<ActivityEntity?> = runCatching {


        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")
        // Get the document reference for the activity
        val docRef = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Activities").document(activityId)
        // we get the snapshot and map it to the DTO
        // If the snapshot exists, we map to domain, convert it to an ActivityEntity and emit
        // Otherwise, we emit null
        val snap = docRef.get().await()
        if (snap.exists()) {
            val dto = snap.toObject(FirestoreActivityDTO::class.java)
            (
                    dto?.toDomain(
                        docId = snap.id,
                        userRef = EntityId(userId),
                        itineraryRef = EntityId(itinId)
                    )
                    )
        } else {
            (null)
        }
    }

    override suspend fun getAllActivitiesForItinerary(
        
        itinId: String
    ): Result<List<ActivityEntity>> = runCatching {

        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")
        // Route to the Activities collection for the given user and itinerary
        val snaps = firestore
            .collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Activities").get().await()

        // For each document, convert the DTO to domain (ActivityEntity):
        val list = snaps.documents.mapNotNull { doc ->
            doc.toObject(FirestoreActivityDTO::class.java)
                ?.toDomain(
                    docId = doc.id,
                    userRef = EntityId(userId),
                    itineraryRef = EntityId(itinId)
                )
        }
        (list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateActivity(
        
        itinId: String,
        activity: ActivityEntity
    ): Result<Unit> = runCatching {

        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")

        // Recostruct the document routes/references:
        val userDoc = firestore.collection("Users").document(userId)
        val itinDoc = userDoc.collection("Itineraries").document(itinId)

        // convert to DTO:
        val dto = FirestoreActivityDTO.fromDomain(
            domain = activity,
            userDoc = userDoc,
            itineraryDoc = itinDoc,
        )

        // Overwrite, set() the specific activity document:
//        firestore.collection("Users").document(userId)
//            .collection("Itineraries").document(itinId)
        itinDoc
            .collection("Activities").document(activity.id)
            .set(dto).await()
    }

    override suspend fun deleteActivity(
        
        itinId: String,
        activityId: String
    ): Result<Unit> = runCatching {

        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")
        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Activities").document(activityId)
            .delete().await()
    }
}