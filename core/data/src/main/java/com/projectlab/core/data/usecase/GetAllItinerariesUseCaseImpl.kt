package com.projectlab.core.data.usecase

import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.repository.ItineraryRepository
import com.projectlab.core.domain.use_cases.itineraries.GetAllItinerariesUseCase
import jakarta.inject.Inject

class GetAllItinerariesUseCaseImpl @Inject constructor(
    private val itineraryRepository: ItineraryRepository
) : GetAllItinerariesUseCase {
    override suspend operator fun invoke(userId : String): Result<List<ItineraryEntity>> {
        //The repository internally retrieves the userId from UserSessionProvider
        return itineraryRepository.getAllItineraries(userId)
    }
}