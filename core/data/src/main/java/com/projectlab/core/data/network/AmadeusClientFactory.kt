package com.projectlab.core.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory for creating a OkHttpClient instance for the Amadeus API.
 * This factory creates an OkHttpClient with logging and authentication interceptors.
 *
 * @param authInterceptor The interceptor for handling authentication.
 * @author ricardoceadev
 */

@Singleton
class AmadeusClientFactory @Inject constructor(
    private val authInterceptor : AuthInterceptor,
    private val tokenAuthenticator: TokenAuthenticator
) : HttpClientFactory {
    override fun createClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)
            .build()
    }

}