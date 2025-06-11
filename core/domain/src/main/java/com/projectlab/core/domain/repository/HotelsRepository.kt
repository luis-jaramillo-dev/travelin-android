package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.model.Response
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * HotelsRepository interface for hotel-related operations.
 *
 * This interface defines the contract for hotel-related data operations,
 * including creating a hotel, retrieving hotels by various criteria,
 * updating and deleting hotels, and managing hotel favorites.
 *
 */

interface HotelsRepository {

    suspend fun createHotel(
        itinId: String,
        hotel: HotelEntity
    ): kotlin.Result<EntityId>
    suspend fun getHotelById(itinId: String, hotelId: String): kotlin.Result<HotelEntity?>
    suspend fun getAllHotels(itinId: String): kotlin.Result<List<HotelEntity>>
    suspend fun updateHotel(itinId: String, hotel: HotelEntity): kotlin.Result<Unit>
    suspend fun deleteHotel(itinId: String, hotelId: String): kotlin.Result<Unit>

    suspend fun getHotelsByCity(
        cityCode: String,
        amenities: String,
        ratings: String
    ): Result<List<Hotel>, DataError.Network>

    suspend fun getHotelsByCoordinates(
        latitude: Double,
        longitude: Double,
        amenities: String,
        ratings: String
    ): Result<List<Hotel>, DataError.Network>

    suspend fun favoriteHotel(hotelId: String): Result<Boolean, DataError.Network>
    suspend fun unfavoriteHotel(hotelId: String): Result<Boolean, DataError.Network>
}