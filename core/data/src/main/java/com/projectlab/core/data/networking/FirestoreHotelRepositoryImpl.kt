package com.projectlab.core.data.networking

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.data.model.dto.FirestoreHotelDTO
import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreHotelRepositoryImpl @Inject constructor (
    private val firestore : FirebaseFirestore
) : HotelRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createHotel(hotel: HotelEntity): Result<EntityId> = runCatching {
        // user reference:
        val userDoc = firestore
            .collection("Users")
            .document(hotel.userRef?.value ?: throw IllegalArgumentException("userRef is null"))
        // itinerary reference:
        val itinDoc = userDoc
            .collection("Itineraries")
            .document(hotel.itineraryRef?.value ?: throw IllegalArgumentException("itineraryRef is null"))
        // Location reference:
        val locationDoc = firestore
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

    override suspend fun getHotelsById(id: String): Flow<HotelEntity?> {
        TODO("Not yet implemented")
    }


}