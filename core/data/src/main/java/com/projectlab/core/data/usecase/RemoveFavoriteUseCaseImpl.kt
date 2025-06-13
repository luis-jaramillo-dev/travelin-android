package com.projectlab.core.data.usecase

import com.projectlab.core.domain.repository.HotelsRepository
import com.projectlab.core.domain.use_cases.hotels.RemoveFavoriteHotelUseCase
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import javax.inject.Inject

class RemoveFavoriteHotelUseCaseImpl @Inject constructor(
    private val repository: HotelsRepository
) : RemoveFavoriteHotelUseCase {
    override suspend operator fun invoke(hotelId: String): Result<Unit, DataError.Network> {
        return repository.removeFavoriteHotelById(hotelId)
    }
}