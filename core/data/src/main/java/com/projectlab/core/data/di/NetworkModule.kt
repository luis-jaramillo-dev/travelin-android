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
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://test.api.amadeus.com/"

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("amadeus_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAmadeusApiService(): AmadeusApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AmadeusApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTokenProvider(
        sharedPreferences: SharedPreferences,
        amadeusApiService: AmadeusApiService
    ): TokenProviderImpl {
        return TokenProviderImpl(sharedPreferences, amadeusApiService)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenProvider: TokenProviderImpl): Interceptor {
        return AuthInterceptor(tokenProvider)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideActivitiesApiService(tokenProvider: TokenProviderImpl): ActivitiesApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenProvider))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ActivitiesApiService::class.java)
    }
}