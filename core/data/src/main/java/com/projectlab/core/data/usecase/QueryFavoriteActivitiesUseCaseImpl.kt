package com.projectlab.core.data.usecase

import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.activities.QueryFavoriteActivitiesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QueryFavoriteActivitiesUseCaseImpl @Inject constructor(
    private val activitiesRepository: ActivityRepository,
    private val userSessionProvider: UserSessionProvider,
) : QueryFavoriteActivitiesUseCase {
    override suspend fun invoke(
        nameQuery: String?,
    ): Result<Flow<FavoriteActivityEntity>> = runCatching {
        val userId = userSessionProvider.getUserSessionId()

        if (userId == null) {
            return Result.failure(NullPointerException("userId is null"))
        }

        activitiesRepository.queryFavoriteActivities(userId, nameQuery)
    }
}
