package com.projectlab.core.domain.use_cases.hotels

import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.repository.HotelsRepository
import javax.inject.Inject

class FavoriteHotelUseCase @Inject constructor(
    private val repository: HotelsRepository
) {
    suspend operator fun invoke(userId: String, hotel: Hotel) =
        repository.favoriteHotel(hotel)
}