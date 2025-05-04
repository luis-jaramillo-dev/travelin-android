package com.projectlab.core.data.repository

import android.content.SharedPreferences
import com.projectlab.booking.data.activities.remote.AmadeusApiService
import com.projectlab.core.data.remote.AccessTokenResponse
import com.projectlab.core.domain.repository.TokenProvider
import javax.inject.Inject
import androidx.core.content.edit

class TokenProviderImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val amadeusApiService: AmadeusApiService
) : TokenProvider {

    private val tokenKey = "access_token"
    private val expirationKey = "access_token_expiration"

    override suspend fun getAccessToken(): String {
        val token = sharedPreferences.getString(tokenKey, null)
        val expirationTime = sharedPreferences.getLong(expirationKey, 0)

        if (token == null || hasTokenExpired(expirationTime)) {
            val newToken = fetchNewAccessToken()
            saveAccessToken(newToken)
            return newToken.accessToken
        }

        return token
    }

    private fun hasTokenExpired(expirationTime: Long): Boolean {
        val currentTime = System.currentTimeMillis() / 1000
        return currentTime >= expirationTime
    }

    private suspend fun fetchNewAccessToken(): AccessTokenResponse {
        val response = amadeusApiService.getAccessToken()
        return response
    }

    private fun saveAccessToken(response: AccessTokenResponse) {
        val expirationTime = System.currentTimeMillis() / 1000 + response.expiresIn
        sharedPreferences.edit() {
            putString(tokenKey, response.accessToken)
                .putLong(expirationKey, expirationTime)
        }
    }
}