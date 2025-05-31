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
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

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

    override suspend fun getActivityById(id: String): Flow<ActivityEntity?> {
        TODO("Not yet implemented")
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
            emit(doc.toObject(FavoriteActivityEntity::class.java))
        }
    }.filter { activity ->
        nameQuery == null
            || nameQuery.isEmpty()
            || activity.name.contains(nameQuery, ignoreCase = true)
    }

    override suspend fun saveFavoriteActivity(
        userId: String,
        activity: FavoriteActivityEntity,
    ): kotlin.Result<String> = runCatching {
        val userDoc = firestore
            .collection("Users")
            .document(userId)

        val favoritesCollection = userDoc.collection("FavoriteActivities")
        val docRef = favoritesCollection.document()
        docRef.set(activity).await()

        docRef.id
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
