package com.projectlab.core.data.networking

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.data.model.dto.FirestoreActivityDTO
import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreActivityRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ActivityRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createActivity(activity: ActivityEntity): Result<EntityId> = runCatching {
        // user reference:
        val userDoc = firestore
            .collection("Users")
            .document(activity.userRef?.value ?: throw IllegalArgumentException("userRef is null"))
        // itineraryReference:
        val itinDoc = userDoc
            .collection("Itineraries")
            .document(activity.itineraryRef?.value ?: throw IllegalArgumentException("itineraryRef is null"))
        // Location reference:
        val locationDoc = firestore
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

    override suspend fun getActivityById(id: String): Flow<ActivityEntity?> {
        TODO("Not yet implemented")
    }

}