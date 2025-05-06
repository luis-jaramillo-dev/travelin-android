package com.projectlab.core.data.usecase

import com.projectlab.core.data.remote.ActivitiesSearchResponse
import com.projectlab.core.data.repository.ActivitiesApiService
import com.projectlab.core.domain.repository.TokenProvider
import javax.inject.Inject

class GetActivitiesUseCase @Inject constructor(
    private val api: ActivitiesApiService,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): ActivitiesSearchResponse {
        val token = tokenProvider.getAccessToken()
        return api.getActivitiesByLocation("Bearer $token", latitude, longitude)
    }
}