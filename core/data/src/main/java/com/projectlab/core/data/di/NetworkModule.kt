package com.projectlab.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.projectlab.core.data.config.AmadeusTokenSerializer
import com.projectlab.core.data.network.AuthInterceptor
import com.projectlab.core.data.proto.AmadeusToken
import com.projectlab.core.data.remote.ActivitiesApiService
import com.projectlab.core.data.remote.AmadeusApiService
import com.projectlab.core.data.repository.AmadeusTokenProviderImpl
import com.projectlab.core.domain.repository.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://test.api.amadeus.com/"

    private val Context.amadeusTokenStore: DataStore<AmadeusToken> by dataStore<AmadeusToken>(
        fileName = "amadeus_token.pb",
        serializer = AmadeusTokenSerializer,
    )

    @Provides
    @Singleton
    fun provideAmadeusTokenStore(
        @ApplicationContext context: Context,
    ): DataStore<AmadeusToken> {
        return context.amadeusTokenStore
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
        amadeusTokenStore: DataStore<AmadeusToken>,
        amadeusApiService: AmadeusApiService,
    ): AmadeusTokenProviderImpl {
        return AmadeusTokenProviderImpl(amadeusTokenStore, amadeusApiService)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenProvider: TokenProvider): Interceptor {
        return AuthInterceptor(tokenProvider)
    }

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
