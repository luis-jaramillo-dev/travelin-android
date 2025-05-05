package com.projectlab.core.data.di

import android.content.Context
import android.content.SharedPreferences
import com.projectlab.core.data.network.AuthInterceptor
import com.projectlab.core.data.remote.AmadeusApiService
import com.projectlab.core.data.repository.ActivitiesApiService
import com.projectlab.core.data.repository.TokenProviderImpl
import com.projectlab.core.domain.repository.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAmadeusApiService(): AmadeusApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.amadeus.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AmadeusApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideActivitiesApiService(): ActivitiesApiService {
        return Retrofit.Builder()
            .baseUrl("https://test.api.amadeus.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ActivitiesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTokenProvider(
        sharedPreferences: SharedPreferences,
        amadeusApiService: AmadeusApiService
    ): TokenProvider {
        return TokenProviderImpl(sharedPreferences, amadeusApiService)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenProvider: TokenProvider): Interceptor {
        return AuthInterceptor(tokenProvider)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }
}