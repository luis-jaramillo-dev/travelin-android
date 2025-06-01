package com.projectlab.core.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.projectlab.core.domain.proto.AmadeusToken
import com.projectlab.core.domain.repository.TokenProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AmadeusTokenProviderImpl @Inject constructor(
    private val amadeusTokenStore: DataStore<AmadeusToken>,
) : TokenProvider {
    private val tag: String = AmadeusTokenProviderImpl::class.java.simpleName

    @Volatile
    private var cachedToken: String? = null
    private var cachedExpiration: Long = 0

    override suspend fun getAccessToken(): String {
        // If the token is cached and not expired, return it
        val now = System.currentTimeMillis() / 1000

        if (cachedToken != null && now < cachedExpiration) {
            return cachedToken!!
        }

        // If the token is not cached or expired, fetch it from the DataStore
        val proto = try {
            amadeusTokenStore.data.first()
        } catch (_: IOException) {
            AmadeusToken.getDefaultInstance()
        }

        val token = proto.accessToken
        val expiration = proto.expiration

        // Update the cached token and expiration time if the token is valid,
        // otherwise return an empty string
        return if (token == null || now < expiration) {
            cachedToken = token
            cachedExpiration = expiration
            token
        } else {
            // Return an empty string if the token is expired or null.
            // Authenticator will handle this case.
            ""
        }
    }

    override fun getCachedToken(): String? {
        val now = System.currentTimeMillis() / 1000
        return if (cachedToken != null && now < cachedExpiration) {
            Log.d(tag, "Using cached token: $cachedToken")
            cachedToken
        } else {
            null
        }
    }

    fun getTokenExpirationInSeconds(): Long {
        val now = System.currentTimeMillis() / 1000
        return if (cachedToken != null && now < cachedExpiration) {
            cachedExpiration - now
        } else {
            -1 // Expression for expired or non-cached token
        }
    }

}
