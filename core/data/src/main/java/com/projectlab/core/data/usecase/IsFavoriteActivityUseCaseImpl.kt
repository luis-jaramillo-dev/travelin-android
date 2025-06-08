package com.projectlab.core.data.usecase

import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.activities.IsFavoriteActivityUseCase
import javax.inject.Inject

class IsFavoriteActivityUseCaseImpl @Inject constructor(
    private val activitiesRepository: ActivityRepository,
) : IsFavoriteActivityUseCase {
    override suspend fun invoke(activityId: String): Result<Boolean> =
        activitiesRepository.isFavoriteActivity(activityId)

}
