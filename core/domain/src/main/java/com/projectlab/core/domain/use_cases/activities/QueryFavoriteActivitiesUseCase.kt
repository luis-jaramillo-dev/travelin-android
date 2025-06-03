package com.projectlab.core.domain.use_cases.activities

import com.projectlab.core.domain.entity.FavoriteActivityEntity
import kotlinx.coroutines.flow.Flow

fun interface QueryFavoriteActivitiesUseCase {
    suspend operator fun invoke(nameQuery: String?): Result<Flow<FavoriteActivityEntity>>
}
