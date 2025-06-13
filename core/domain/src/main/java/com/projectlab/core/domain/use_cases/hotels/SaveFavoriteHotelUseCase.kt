package com.projectlab.core.domain.use_cases.hotels

import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

fun interface SaveFavoriteHotelUseCase {
    suspend operator fun invoke(hotel: Hotel): Result<Unit, DataError>
}