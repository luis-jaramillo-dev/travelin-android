package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.model.EntityId

interface HotelRepository {
    suspend fun createHotel(hotel: HotelEntity): Result<EntityId>
    suspend fun getHotelsById(id: String): HotelEntity?
}