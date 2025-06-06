package com.projectlab.core.data.usecase

import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.activities.SaveFavoriteActivityUseCase
import javax.inject.Inject

class SaveFavoriteActivityUseCaseImpl @Inject constructor(
    private val activitiesRepository: ActivityRepository,
) : SaveFavoriteActivityUseCase {
    override suspend fun invoke(activity: FavoriteActivityEntity): Result<Unit> =
        activitiesRepository.saveFavoriteActivity(activity)

}
