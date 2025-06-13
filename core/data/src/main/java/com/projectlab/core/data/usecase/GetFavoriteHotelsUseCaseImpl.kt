package com.projectlab.core.data.usecase

import com.projectlab.core.domain.mapper.toHotel
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.repository.HotelsRepository
import com.projectlab.core.domain.use_cases.hotels.GetFavoriteHotelsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteHotelsUseCaseImpl @Inject constructor(
    private val repository: HotelsRepository
) : GetFavoriteHotelsUseCase {
    override operator fun invoke(): Flow<List<Hotel>> {
        return repository.getFavoriteHotels()
            .map { hotels -> hotels.map { it.toHotel() } }
    }
}