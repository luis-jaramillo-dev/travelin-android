package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow

/**
 * HotelRepository interface for hotel-related operations.
 *
 * This interface defines the contract for hotel-related data operations, including creating a hotel
 * and retrieving a hotel by its ID.
 *
 * @author ricardoceadev
 */

interface HotelRepository {
    suspend fun createHotel(hotel: HotelEntity): Result<EntityId>
    suspend fun getHotelsById(id: String): Flow<HotelEntity?>
}