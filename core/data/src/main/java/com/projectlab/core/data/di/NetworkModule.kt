package com.projectlab.core.data.di

import android.content.Context
import android.content.SharedPreferences
import com.projectlab.core.data.network.AmadeusClientFactory
import com.projectlab.core.data.network.HttpClientFactory
import com.projectlab.core.data.network.AuthInterceptor
import com.projectlab.core.data.remote.AmadeusApiService
import com.projectlab.core.data.remote.ActivitiesApiService
import com.projectlab.core.data.remote.ActivityApiService
import com.projectlab.core.data.repository.AmadeusTokenProviderImpl
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
     * Provides the concrete factory to create OkHttpClient
     * configured for Amadeus (logging, auth interceptor...).
     *
     * @param authInterceptor Interceptor that adds the Bearer token.
     * @return HttpClientFactory for Amadeus.
    */
    @Provides
    @Singleton
    fun provideAmadeusClientFactory (
        authInterceptor: AuthInterceptor
    ) : HttpClientFactory = AmadeusClientFactory(authInterceptor)

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
    ): AmadeusTokenProviderImpl {
        return AmadeusTokenProviderImpl(sharedPreferences, amadeusApiService)
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
     * Provides OkHttpClient using the factory injected.
     *
     * @param factory Factory that knows how to configure interceptors and logging.
     * @return An OkHttpClient ready to be used with Retrofit.
     *
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        factory: HttpClientFactory
    ): OkHttpClient = factory.createClient()

    /**
     * Provides ActivitiesApiService targeting BASE_URL using OkHttpClient
     * with auth and logging.
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

    @Provides
    @Singleton
    fun provideActivityApiService(okHttpClient: OkHttpClient): ActivityApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ActivityApiService::class.java)
    }
}