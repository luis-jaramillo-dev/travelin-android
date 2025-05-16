package com.projectlab.core.data.repository

import android.content.SharedPreferences
import com.projectlab.core.data.remote.AmadeusApiService
import com.projectlab.core.data.remote.AccessTokenResponse
import com.projectlab.core.domain.repository.TokenProvider
import javax.inject.Inject
import com.projectlab.core.data.config.Config

class TokenProviderImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val amadeusApiService: AmadeusApiService
) : TokenProvider {

    private val tokenKey = "access_token"
    private val expirationKey = "access_token_expiration"

    @Volatile
    private var cachedToken: String? = null
    private var cachedExpiration: Long = 0

    override suspend fun getAccessToken(): String {
        val now = System.currentTimeMillis() / 1000

        if (cachedToken != null && now < cachedExpiration) {
            return cachedToken!!
        }

        val token = sharedPreferences.getString(tokenKey, null)
        val expiration = sharedPreferences.getLong(expirationKey, 0)

        return if (token == null || now >= expiration) {
            val newToken = fetchNewAccessToken()
            saveAccessToken(newToken)
            newToken.accessToken
        } else {
            cachedToken = token
            cachedExpiration = expiration
            token
        }
    }

    override fun getCachedToken(): String? {
        val now = System.currentTimeMillis() / 1000
        return if (cachedToken != null && now < cachedExpiration) {
            cachedToken
        } else {
            val token = sharedPreferences.getString(tokenKey, null)
            val expiration = sharedPreferences.getLong(expirationKey, 0)
            if (token != null && now < expiration) {
                cachedToken = token
                cachedExpiration = expiration
                token
            } else{
                null
            }
        }
    }

    private suspend fun fetchNewAccessToken(): AccessTokenResponse {
        return try {
            val response = amadeusApiService.getAccessToken(
                clientId = Config.apiKey,
                clientSecret = Config.apiSecret
            )
            response
        } catch (e: Exception) {
            throw e
        }
    }

    private fun saveAccessToken(response: AccessTokenResponse) {
        val expirationTime = System.currentTimeMillis() / 1000 + response.expiresIn
        cachedToken = response.accessToken
        cachedExpiration = expirationTime

        sharedPreferences.edit().apply {
            putString(tokenKey, response.accessToken)
            putLong(expirationKey, expirationTime)
            apply()
        }
    }
}