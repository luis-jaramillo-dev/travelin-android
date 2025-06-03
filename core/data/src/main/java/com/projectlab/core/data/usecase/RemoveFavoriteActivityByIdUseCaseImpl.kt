package com.projectlab.core.data.usecase

import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.activities.RemoveFavoriteActivityByIdUseCase
import javax.inject.Inject

class RemoveFavoriteActivityByIdUseCaseImpl @Inject constructor(
    private val activitiesRepository: ActivityRepository,
    private val userSessionProvider: UserSessionProvider,
) : RemoveFavoriteActivityByIdUseCase {
    override suspend fun invoke(activityId: String): Result<Unit> = runCatching {
        val userId = userSessionProvider.getUserSessionId()

        if (userId == null) {
            return Result.failure(NullPointerException("userId is null"))
        }

        activitiesRepository.removeFavoriteActivityById(userId, activityId)
    }
}
