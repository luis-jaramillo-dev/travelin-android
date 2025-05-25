package com.projectlab.core.data.network

import android.util.Log
import com.projectlab.core.domain.repository.TokenProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * AuthInterceptor is an OkHttp interceptor that adds the Authorization header to requests.
 * It retrieves the access token from the TokenProvider and adds it to the request headers.
 *
 * @param tokenProvider The TokenProvider instance used to retrieve the access token.
 */

class AuthInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider
) : Interceptor {

    private val TAG = "AuthInterceptor" // logcat tag

    override fun intercept(chain: Interceptor.Chain): Response {

        // Log.d(TAG, "🔑 AuthInterceptor: intercept() initiated") // logcat message TODO: erase this one before production release

        val token = runBlocking {
            tokenProvider.getAccessToken()
        }

        // Log.d(TAG, "🔑 Token gotten: $token") // logcat message TODO: erase this one before production release

        val requestBuilder = chain.request().newBuilder()

        if (token.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
            // Log.d(TAG, "Added Authorization header") // logcat message TODO: erase this one before production release
        } else {
        }

        return chain.proceed(requestBuilder.build())
    }
}