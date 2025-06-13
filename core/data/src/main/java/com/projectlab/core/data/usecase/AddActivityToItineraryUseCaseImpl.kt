package com.projectlab.core.data.usecase

import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.ItineraryRepository
import com.projectlab.core.domain.use_cases.itineraries.AddActivityToItineraryUseCase
import jakarta.inject.Inject

class AddActivityToItineraryUseCaseImpl  @Inject constructor(
    private val itineraryRepository: ItineraryRepository,
    private val activityRepository: ActivityRepository,
) : AddActivityToItineraryUseCase {
    override suspend fun invoke(
        userId: String,
        activity: ActivityEntity
    ): Result<Unit> {
        var itineraryList : List<ItineraryEntity>? = emptyList()
        val result = itineraryRepository.getAllItineraries(userId)
        var itineraryId : String? = null

        if (result.isSuccess) {
            itineraryList = result.getOrNull()
        }
        if (itineraryList?.isEmpty() == true || itineraryList == null) {
            val resultId  = itineraryRepository.createItinerary(
                ItineraryEntity(
                    id = userId,
                    title = "Trip to Rome",
                    startDate = activity.activityDate,
                    endDate = activity.activityDate,
                    totalItineraryPrice = 1000.0,
                )
            ).getOrNull()
            if (resultId != null) {
                itineraryId = resultId.value
            }
        } else {
            itineraryId = itineraryList.get(0).id
        }

        try {
            activityRepository.createActivity(itineraryId!!, activity)
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}