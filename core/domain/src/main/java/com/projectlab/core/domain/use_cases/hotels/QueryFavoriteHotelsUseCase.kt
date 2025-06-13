package com.projectlab.core.domain.use_cases.hotels

import com.projectlab.core.domain.entity.FavoriteHotelEntity
import com.projectlab.core.domain.repository.HotelsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QueryFavoriteHotelsUseCase @Inject constructor(
    private val repository: HotelsRepository
) {
    operator fun invoke(query: String): Flow<FavoriteHotelEntity> {
        return repository.queryFavoriteHotels(query)
    }
}
