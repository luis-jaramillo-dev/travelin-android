package com.projectlab.core.domain.repository

import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

interface HotelsRepository {
    suspend fun getHotelsByCity(
        cityCode: String,
        amenities: String,
        ratings: String
    ): Result<List<Hotel>, DataError.Network>
}