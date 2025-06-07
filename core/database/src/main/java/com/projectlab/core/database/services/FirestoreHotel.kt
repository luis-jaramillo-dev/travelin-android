package com.projectlab.core.database.services

import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow

/**
 * FirestoreHotel interface for hotel-related operations.
 *
 * This interface defines the contract for hotel-related data operations, including creating a hotel
 * and retrieving a hotel by its ID.
 *
 * @author ricardoceadev
 */

interface FirestoreHotel {
    suspend fun createHotel(
        itinId: String,
        hotel: HotelEntity,
    ): Result<EntityId>
    suspend fun getHotelsById(itinId: String, hotelId: String): Result<HotelEntity?>
    suspend fun getAllHotelsForItinerary(itinId: String): Result<List<HotelEntity>>
    suspend fun updateHotel(
        itinId: String,
        hotel: HotelEntity,
    ): Result<Unit>
    suspend fun deleteHotel( itinId: String, hotelId: String): Result<Unit>
}