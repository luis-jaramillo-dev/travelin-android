package com.projectlab.core.data.usecase

import com.projectlab.core.domain.mapper.toFavoriteHotelEntity
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.repository.HotelsRepository
import com.projectlab.core.domain.use_cases.hotels.SaveFavoriteHotelUseCase
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import javax.inject.Inject

class SaveFavoriteHotelUseCaseImpl @Inject constructor(
    private val repository: HotelsRepository
) : SaveFavoriteHotelUseCase {
    override suspend operator fun invoke(hotel: Hotel): Result<Unit, DataError> {
        return repository.saveFavoriteHotel(hotel.toFavoriteHotelEntity())
    }
}