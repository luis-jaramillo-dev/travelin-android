package com.projectlab.booking.data.retrofit

import com.projectlab.core.data.remote.AmadeusApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthRetrofitClient {

    private const val BASE_URL = "https://test.api.amadeus.com/"

    val service: AmadeusApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AmadeusApiService::class.java)
    }
}