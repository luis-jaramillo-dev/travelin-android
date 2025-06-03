package com.projectlab.core.domain.use_cases.activities

fun interface IsFavoriteActivityUseCase {
    suspend operator fun invoke(activityId: String): Result<Boolean>
}
