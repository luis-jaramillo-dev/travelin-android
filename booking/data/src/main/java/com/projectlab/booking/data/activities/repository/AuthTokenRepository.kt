package com.projectlab.booking.data.activities.repository

import com.projectlab.booking.data.activities.remote.AmadeusApiService
import com.projectlab.core.data.config.Config

class AuthTokenRepository(
    private val apiService: AmadeusApiService
) {
    suspend fun fetchAccessToken(): String {
        val response = apiService.getAccessToken(
            clientId = Config.apiKey,
            clientSecret = Config.apiSecret
        )
        return response.accessToken
    }
}