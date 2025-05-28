package com.projectlab.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.projectlab.core.data.config.AmadeusTokenSerializer
import com.projectlab.core.data.config.OnboardingFlagSerializer
import com.projectlab.core.data.config.SearchHistorySerializer
import com.projectlab.core.data.network.AmadeusClientFactory
import com.projectlab.core.data.network.AuthInterceptor
import com.projectlab.core.data.network.HttpClientFactory
import com.projectlab.core.data.network.AmadeusTokenAuthenticator
import com.projectlab.core.data.remote.ActivitiesApiService
import com.projectlab.core.data.remote.ActivityApiService
import com.projectlab.core.data.remote.AmadeusApiService
import com.projectlab.core.data.repository.AmadeusTokenProviderImpl
import com.projectlab.core.data.repository.OnboardingFlagProviderImpl
import com.projectlab.core.data.repository.SearchHistoryProviderImpl
import com.projectlab.core.domain.proto.AmadeusToken
import com.projectlab.core.domain.proto.OnboardingFlag
import com.projectlab.core.domain.proto.SearchHistory
import com.projectlab.core.domain.repository.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * NetworkModule provides the necessary dependencies (with Dagger Hilt) for network operations.
 * It includes the Retrofit instance, OkHttpClient, and DataStore.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://test.api.amadeus.com/"

    private val Context.onboardingFlagStore: DataStore<OnboardingFlag> by dataStore<OnboardingFlag>(
        fileName = "onboarding_flag.pb",
        serializer = OnboardingFlagSerializer,
    )

    private val Context.amadeusTokenStore: DataStore<AmadeusToken> by dataStore<AmadeusToken>(
        fileName = "amadeus_token.pb",
        serializer = AmadeusTokenSerializer,
    )

    private val Context.searchHistoryStore: DataStore<SearchHistory> by dataStore<SearchHistory>(
        fileName = "search_history.pb",
        serializer = SearchHistorySerializer,
    )

    /**
     * Provides a singleton instance of AmadeusToken DataStore.
     * Provides a singleton instance of OnboardingFlag DataStore.
     *
     * @param context The application context.
     * @return An OnboardingFlag DataStore instance.
     */
    @Provides
    @Singleton
    fun provideOnboardingFlagStore(
        @ApplicationContext context: Context,
    ): DataStore<OnboardingFlag> {
        return context.onboardingFlagStore
    }

    /**
     * Provides a singleton instance of DataStore.
     *
     * @param context The application context.
     * @return A DataStore instance.
     */
    @Provides
    @Singleton
    fun provideAmadeusTokenStore(
        @ApplicationContext context: Context,
    ): DataStore<AmadeusToken> {
        return context.amadeusTokenStore
    }

    /**
     * Provides a singleton instance of SearchHistory DataStore.
     *
     * @param context The application context.
     * @return A DataStore instance.
     */
    @Provides
    @Singleton
    fun provideSearchHistoryStore(
        @ApplicationContext context: Context,
    ): DataStore<SearchHistory> {
        return context.searchHistoryStore
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
    fun provideAmadeusClientFactory(
        authInterceptor: AuthInterceptor,
        amadeusTokenAuthenticator: AmadeusTokenAuthenticator,
    ): HttpClientFactory = AmadeusClientFactory(authInterceptor, amadeusTokenAuthenticator)

    /**
     * Provides a singleton instance of OnboardingFlagProvider.
     *
     * @param onboardingFlagStore The OnboardingFlag DataStore instance.
     * @return A OnboardingFlagProvider instance.
     */
    @Provides
    @Singleton
    fun provideOnboardingFlagProvider(
        onboardingFlagStore: DataStore<OnboardingFlag>,
    ): OnboardingFlagProviderImpl {
        return OnboardingFlagProviderImpl(onboardingFlagStore)
    }

    /**
     * Provides a singleton instance of TokenProvider.
     *
     * @param amadeusTokenStore The DataStore instance.
     * @return A TokenProvider instance.
     */
    @Provides
    @Singleton
    fun provideTokenProvider(
        amadeusTokenStore: DataStore<AmadeusToken>
    ): AmadeusTokenProviderImpl {
        return AmadeusTokenProviderImpl(amadeusTokenStore)
    }

    /**
     * Provides a singleton instance of SearchHistoryProvider.
     *
     * @param searchHistoryStore The SearchHistory DataStore instance.
     * @return A SearchHistoryProvider instance.
     */
    @Provides
    @Singleton
    fun provideSearchHistoryProvider(
        searchHistoryStore: DataStore<SearchHistory>,
    ): SearchHistoryProviderImpl {
        return SearchHistoryProviderImpl(searchHistoryStore)
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

    @Provides @Singleton
    fun provideTokenAuthenticator(
        tokenProvider: TokenProvider,
        amadeusApiService: AmadeusApiService,
        amadeusTokenStore: DataStore<AmadeusToken>
    ): Authenticator = AmadeusTokenAuthenticator(tokenProvider, amadeusApiService, amadeusTokenStore)



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
        factory: HttpClientFactory,
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
    fun provideActivityApiService(okHttpClient: OkHttpClient): ActivityApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ActivityApiService::class.java)
    }
}
