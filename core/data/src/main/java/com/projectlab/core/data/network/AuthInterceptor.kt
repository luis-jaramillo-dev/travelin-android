package com.projectlab.core.data.network

import android.util.Log
import com.projectlab.core.domain.repository.TokenProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * AuthInterceptor is an OkHttp interceptor that which is responsible for intercepting
 * each HTTP request that leaves our app to Amadeus (or any other service), and adding
 * the authorization header (Authorization: Bearer <token>) before sending it.
 *
 * @param tokenProvider The TokenProvider instance used to retrieve the access token.
 */

class AuthInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider
) : Interceptor {

    private val TAG = "AuthInterceptor" // logcat tag

    override fun intercept(chain: Interceptor.Chain): Response {
        // logcat message TODO: erase this one before production release
        Log.d(TAG, "🐱‍🏍 intercept(): initiated")
        // We retrieve the access token from the TokenProvider.
        val token = runBlocking {
            tokenProvider.getAccessToken()
        }
        // logcat message TODO: erase this one before production release
        Log.d(TAG, "🔑 Token gotten: '${token.ifEmpty { "<EMPTY>" }}'")
        Log.d(TAG, "Token expiration time: ${calculateRemainTokenTimeInSeconds()}")

        // If the token is empty, we will not add the Authorization header.
        val requestBuilder = chain.request().newBuilder()
        if (token.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
            // logcat message TODO: erase this one before production release
            Log.d(TAG, "✏ Added Authorization header")
        } else {
            // logcat message TODO: erase this one before production release
            Log.w(TAG, "⚠️ Token empty: we send request without Authorization")
        }

        // We build the request with the Authorization header (if present)
        // and proceed with the chain. If the token is empty, the request will be sent
        // without the Authorization header and the server will respond with a 401 Unauthorized.
        val response = chain.proceed(requestBuilder.build())
        // logcat message TODO: erase this one before production release
        Log.d(TAG, "✅ intercept(): request sent, HTTP code: ${response.code}")
        return response
    }

    /**
     * Calculates the remaining time for the token to expire in seconds.
     * If the token is provided by AmadeusTokenProviderImpl, it retrieves the expiration time.
     * Otherwise, it returns a message indicating that the remaining time cannot be determined.
     *
     * @return A string indicating the remaining time for the token to expire.
     */
    private fun calculateRemainTokenTimeInSeconds(): String {
        return if (tokenProvider is com.projectlab.core.data.repository.AmadeusTokenProviderImpl) {
            val secondsLeft =
                (tokenProvider as com.projectlab.core.data.repository.AmadeusTokenProviderImpl)
                .getTokenExpirationInSeconds()

            if (secondsLeft > 0) {
                "✅ Valid token, expires in $secondsLeft seconds"
            } else {
                "⛔ Expired token or not available, cannot determine remaining time"
            }
        } else {
            "ℹ️ We cannot determine the remaining time for the token"
        }
    }
}