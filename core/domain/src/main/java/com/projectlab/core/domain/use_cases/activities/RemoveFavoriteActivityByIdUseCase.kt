package com.projectlab.core.domain.use_cases.activities

fun interface RemoveFavoriteActivityByIdUseCase {
    suspend operator fun invoke(activityId: String): Result<Unit>
}
