package com.projectlab.core.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.projectlab.core.data.config.Config
import com.projectlab.core.data.remote.AccessTokenResponse
import com.projectlab.core.data.remote.AmadeusApiService
import com.projectlab.core.domain.proto.AmadeusToken
import com.projectlab.core.domain.repository.TokenProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AmadeusTokenProviderImpl @Inject constructor(
    private val amadeusTokenStore: DataStore<AmadeusToken>,
    private val amadeusApiService: AmadeusApiService,
) : TokenProvider {
    private val tag: String = AmadeusTokenProviderImpl::class.java.simpleName

    @Volatile
    private var cachedToken: String? = null
    private var cachedExpiration: Long = 0

    override suspend fun getAccessToken(): String {
        val now = System.currentTimeMillis() / 1000

        if (cachedToken != null && now < cachedExpiration) {
            return cachedToken!!
        }

        val amadeusToken = try {
            amadeusTokenStore.data.first()
        } catch (_: IOException) {
            AmadeusToken.getDefaultInstance()
        }

        val token = amadeusToken.accessToken
        val expiration = amadeusToken.expiration

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
            Log.d(tag, "Using cached token: $cachedToken")
            cachedToken
        } else {
            // it could be this instead:
            // cachedToken = null
            // cachedExpiration = 0
            // return null

            val amadeusToken = runBlocking {
                try {
                    amadeusTokenStore.data.first()
                } catch (_: IOException) {
                    AmadeusToken.getDefaultInstance()
                }
            }

            val token = amadeusToken.accessToken
            val expiration = amadeusToken.expiration

            Log.d(tag, "Fetched token from Proto DataStore: $token, expiration=$expiration")

            if (token != null && now < expiration) {
                cachedToken = token
                cachedExpiration = expiration
                Log.d(tag, "Token is valid, using it: $cachedToken")
                token
            } else {
                Log.d(tag, "Token is expired or null")
                null
            }
        }
    }

    private suspend fun fetchNewAccessToken(): AccessTokenResponse {
        Log.d(tag, "Fetching new access token from API")
        Log.d(tag, "API_KEY=${Config.apiKey}, API_SECRET=${Config.apiSecret}")
        return try {
            val response = amadeusApiService.getAccessToken(
                clientId = Config.apiKey,
                clientSecret = Config.apiSecret
            )
            Log.d(tag, "Fetched token: ${response.accessToken}")
            response
        } catch (e: Exception) {
            Log.e(tag, "Failed to fetch token: ${e.localizedMessage}", e)
            throw e
        }
    }

    private suspend fun saveAccessToken(response: AccessTokenResponse) {
        val expirationTime = System.currentTimeMillis() / 1000 + response.expiresIn
        cachedToken = response.accessToken
        cachedExpiration = expirationTime

        amadeusTokenStore.updateData { preferences ->
            preferences.toBuilder()
                .setAccessToken(cachedToken)
                .setExpiration(cachedExpiration)
                .build()
        }
    }
}
