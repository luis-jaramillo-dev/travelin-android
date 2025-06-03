package com.projectlab.core.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.data.mapper.toDomain
import com.projectlab.core.data.model.dto.FirestoreActivityDTO
import com.projectlab.core.data.remote.ActivityApiService
import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * ActivityRepositoryImpl is a concrete implementation of the ActivityRepository interface.
 * It provides methods to interact with activities in Firestore and external APIs.
 *
 * @param firestore The FirebaseFirestore instance used to interact with Firestore.
 * @param activityApiService The ActivityApiService instance used to fetch activities from an external API.
 */

class ActivityRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val activityApiService: ActivityApiService,
) : ActivityRepository {
    override suspend fun getAPIActivityById(
        id: String,
    ): Result<Activity, DataError.Network> {
        return try {
            val response = activityApiService.getActivityById(id)
            Result.Success(response.data.toDomain())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(DataError.Network.UNKNOWN)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createActivity(activity: ActivityEntity): kotlin.Result<EntityId> =
        runCatching {
            var userDoc = firestore
                .collection("Users")
                .document(
                    activity.userRef?.value ?: throw IllegalArgumentException("userRef is null")
                )

            var itineraryDoc = userDoc
                .collection("Itineraries")
                .document(
                    activity.itineraryRef?.value
                        ?: throw IllegalArgumentException("itineraryRef is null")
                )

            var locationDoc = firestore
                .collection("Locations")
                .document(
                    activity.locationRef?.value
                        ?: throw IllegalArgumentException("locationRef is null")
                )

            val dto = FirestoreActivityDTO.fromDomain(activity, userDoc, itineraryDoc, locationDoc)
            val activityCol = itineraryDoc.collection("Activities")
            val docRef = activityCol.document()
            docRef.set(dto).await()

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
        // If the snapshot exists, we map to domain, convert it to an ActivityEntity and emit
        // Otherwise, we emit null
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
        emit(list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateActivity(activity: ActivityEntity): kotlin.Result<Unit> = runCatching {
        // We retrieve the user and itinerary references from the object reference
        val userId = activity.userRef?.value
            ?: throw IllegalArgumentException("userRef is null")
        val itinId = activity.itineraryRef?.value
            ?: throw IllegalArgumentException("itineraryRef is null")

        // Recostruct the document routes/references:
        val userDoc = firestore.collection("Users").document(userId)
        val itinDoc = userDoc.collection("Itineraries").document(itinId)

        // convert to DTO:
        val dto = FirestoreActivityDTO.fromDomain(
            domain = activity,
            userDoc = userDoc,
            itineraryDoc = itinDoc,
            locationDoc = firestore.collection("Locations")
                .document(activity.locationRef?.value ?: throw IllegalArgumentException("locationRef is null"))
        )

        // Overwrite, set() the specific activity document:
        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Activities").document(activity.id)
            .set(dto).await()
    }

    override suspend fun deleteActivity(
        userId: String,
        itinId: String,
        activityId: String
    ): kotlin.Result<Unit> = runCatching {
        firestore.collection("Users").document(userId)
            .collection("Itineraries").document(itinId)
            .collection("Activities").document(activityId)
            .delete().await()
    }

    override fun queryFavoriteActivities(
        userId: String,
        nameQuery: String?,
    ): Flow<FavoriteActivityEntity> = flow {
        val userDoc = firestore
            .collection("Users")
            .document(userId)

        val documents = userDoc
            .collection("FavoriteActivities")
            .get()
            .await()

        documents.map { doc ->
            val activity = doc.toObject(FavoriteActivityEntity::class.java)

            val include = nameQuery == null
                || nameQuery.isEmpty()
                || activity.name.contains(nameQuery, ignoreCase = true)

            if (include) {
                emit(activity)
            }
        }
    }

    override suspend fun isFavoriteActivity(
        userId: String,
        activityId: String,
    ): kotlin.Result<Boolean> = runCatching {
        val userDoc = firestore
            .collection("Users")
            .document(userId)

        val document = userDoc
            .collection("FavoriteActivities")
            .document(activityId)
            .get()
            .await()

        document != null
    }

    override suspend fun saveFavoriteActivity(
        userId: String,
        activity: FavoriteActivityEntity,
    ): kotlin.Result<Unit> = runCatching {
        val userDoc = firestore
            .collection("Users")
            .document(userId)

        val favoritesCollection = userDoc.collection("FavoriteActivities")
        val docRef = favoritesCollection.document(activity.id)
        docRef.set(activity).await()
    }

    override suspend fun removeFavoriteActivityById(
        userId: String,
        activityId: String,
    ): kotlin.Result<Unit> = runCatching {
        val userDoc = firestore
            .collection("Users")
            .document(userId)

        val activityDoc = userDoc
            .collection("FavoriteActivities")
            .document(activityId)

        activityDoc.delete().await()
    }

    override suspend fun getActivitiesByCoordinates(
        latitude: Double,
        longitude: Double,
    ): Result<List<Activity>, DataError.Network> {
        return try {
            val response = activityApiService.getActivitiesByLocation(latitude, longitude)
            response.data
            Result.Success(response.data.map { it.toDomain() })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(DataError.Network.UNKNOWN)
        }
    }
}
