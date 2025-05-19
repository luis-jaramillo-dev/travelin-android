package com.projectlab.core.data.network

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

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenProvider.getAccessToken()
        }

        val requestBuilder = chain.request().newBuilder()

        if (token.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        } else {
        }

        return chain.proceed(requestBuilder.build())
    }
}