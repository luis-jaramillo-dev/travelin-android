package com.projectlab.core.domain.use_cases.activities

import com.projectlab.core.domain.entity.FavoriteActivityEntity

fun interface SaveFavoriteActivityUseCase {
    suspend operator fun invoke(activity: FavoriteActivityEntity): Result<Unit>
}
