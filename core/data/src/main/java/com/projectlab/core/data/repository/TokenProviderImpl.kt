package com.projectlab.core.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.projectlab.core.data.remote.AmadeusApiService
import com.projectlab.core.data.remote.AccessTokenResponse
import com.projectlab.core.domain.repository.TokenProvider
import javax.inject.Inject
import androidx.core.content.edit
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
            Log.d("TokenProvider", "Using cached token: $cachedToken")
            cachedToken
        } else {
            val token = sharedPreferences.getString(tokenKey, null)
            val expiration = sharedPreferences.getLong(expirationKey, 0)
            Log.d("TokenProvider", "Fetched token from SharedPreferences: $token, expiration=$expiration")
            if (token != null && now < expiration) {
                cachedToken = token
                cachedExpiration = expiration
                Log.d("TokenProvider", "Token is valid, using it: $cachedToken")
                token
            } else{
                Log.d("TokenProvider", "Token is expired or null")
                null
            }
        }
    }

    private suspend fun fetchNewAccessToken(): AccessTokenResponse {
        Log.d("TokenProvider", "Fetching new access token from API")
        Log.d("TokenProvider", "API_KEY=${Config.apiKey}, API_SECRET=${Config.apiSecret}")
        return try {
            val response = amadeusApiService.getAccessToken(
                clientId = Config.apiKey,
                clientSecret = Config.apiSecret
            )
            Log.d("TokenProvider", "Fetched token: ${response.accessToken}")
            response
        } catch (e: Exception) {
            Log.e("TokenProvider", "Failed to fetch token: ${e.localizedMessage}", e)
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