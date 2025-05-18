package com.projectlab.core.data.di

import android.content.Context
import android.content.SharedPreferences
import com.projectlab.core.data.network.AuthInterceptor
import com.projectlab.core.data.remote.AmadeusApiService
import com.projectlab.core.data.remote.ActivitiesApiService
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

/**
 * NetworkModule provides the necessary dependencies (with Dagger Hilt) for network operations.
 * It includes the Retrofit instance, OkHttpClient, and SharedPreferences.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://test.api.amadeus.com/"

    /**
     * Provides a singleton instance of SharedPreferences.
     *
     * @param context The application context.
     * @return A SharedPreferences instance.
     */
    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("amadeus_prefs", Context.MODE_PRIVATE)
    }

    /**
     * Provides a singleton instance of AmadeusApiService.
     *
     * @return An AmadeusApiService instance.
     */
    @Provides
    @Singleton
    fun provideAmadeusApiService(): AmadeusApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AmadeusApiService::class.java)
    }

    /**
     * Provides a singleton instance of TokenProvider.
     *
     * @param sharedPreferences The SharedPreferences instance.
     * @param amadeusApiService The AmadeusApiService instance.
     * @return A TokenProvider instance.
     */
    @Provides
    @Singleton
    fun provideTokenProvider(
        sharedPreferences: SharedPreferences,
        amadeusApiService: AmadeusApiService
    ): TokenProviderImpl {
        return TokenProviderImpl(sharedPreferences, amadeusApiService)
    }

    /**
     * Provides a singleton instance of AuthInterceptor.
     *
     * @param tokenProvider The TokenProvider instance.
     * @return An AuthInterceptor instance.
     */
    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenProvider: TokenProvider): Interceptor {
        return AuthInterceptor(tokenProvider)
    }

    /**
     * Provides a singleton instance of OkHttpClient.
     *
     * @param authInterceptor The AuthInterceptor instance.
     * @return An OkHttpClient instance.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .build()
    }

    /**
     * Provides a singleton instance of ActivitiesApiService.
     *
     * @param okHttpClient The OkHttpClient instance.
     * @return An ActivitiesApiService instance.
     */
    @Provides
    @Singleton
    fun provideActivitiesApiService(okHttpClient: OkHttpClient): ActivitiesApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ActivitiesApiService::class.java)
    }
}