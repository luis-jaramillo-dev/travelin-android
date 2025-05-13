package com.projectlab.booking.data.retrofit

import com.projectlab.core.domain.repository.TokenProvider
import com.projectlab.core.data.remote.ActivitiesApiService
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://test.api.amadeus.com/"

    fun create(tokenProvider: TokenProvider): ActivitiesApiService {
        val token = runBlocking { tokenProvider.getAccessToken() }

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ActivitiesApiService::class.java)
    }
}