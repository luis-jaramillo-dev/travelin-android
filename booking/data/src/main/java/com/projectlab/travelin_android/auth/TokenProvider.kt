package com.projectlab.travelin_android.auth

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Named
import kotlinx.coroutines.runBlocking
import android.util.Log

class TokenProvider @Inject constructor(
    private val authApi: AuthApi,
    @ApplicationContext private val context: Context,
    @Named("apiKey") private val apiKey: String,
    @Named("apiSecret") private val apiSecret: String
) {
    private var token: String? = null

    suspend fun fetchToken(): String {
        if (token == null) {
            val response = authApi.getToken(
                clientId = apiKey,
                clientSecret = apiSecret
            )
            token = response.access_token
            Log.i("api key",apiKey)
            Log.i("auth secret",apiSecret)
        }
        return token!!
    }
}
