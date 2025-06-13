package com.projectlab.core.domain.use_cases.hotels

import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

fun interface RemoveFavoriteHotelUseCase {
    suspend operator fun invoke(hotelId: String): Result<Unit, DataError.Network>
}