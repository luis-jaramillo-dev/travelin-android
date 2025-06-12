package com.projectlab.core.domain.use_cases.hotels

import com.projectlab.core.domain.model.Hotel
import kotlinx.coroutines.flow.Flow

fun interface GetFavoriteHotelsUseCase {
    operator fun invoke(): Flow<List<Hotel>>
}