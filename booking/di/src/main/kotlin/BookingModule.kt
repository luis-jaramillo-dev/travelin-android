package com.projectlab.travelin_android.booking.di

import android.content.Context
import api.FlightApiService
import auth.AuthApi
import auth.AuthInterceptor
import auth.TokenProvider
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import di.ApiSecret
import di.ApiKey
import di.BaseUrl
import javax.inject.Singleton
import okhttp3.OkHttpClient
import repository.FlightRepository
import repository.FlightRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log


@Module
@InstallIn(SingletonComponent::class)
object BookingModule {
    @Provides
    @Singleton
    fun provideAuthApi(@BaseUrl baseUrl:String): AuthApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)

    }

    @Provides
    @Singleton
    fun provideTokenProvider(authApi: AuthApi, @ApplicationContext context: Context,
                             @ApiKey apiKey: String,
                             @ApiSecret apiSecret: String): TokenProvider {
        return TokenProvider(
            authApi, context,
            apiKey = apiKey,
            apiSecret = apiSecret,
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenProvider: TokenProvider): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenProvider))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient,@BaseUrl baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

   /* @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://test.api.amadeus.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/


    @Provides
    fun provideApi(retrofit: Retrofit): FlightApiService =
        retrofit.create(FlightApiService::class.java)

    /*@Provides
    @Singleton
    fun provideBookingApi(retrofit: Retrofit): BookingApi {
        return retrofit.create(BookingApi::class.java)
    }*/

   /* @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()*/

    @Provides
    fun provideRepository(
        api: FlightApiService,
        firestore: FirebaseFirestore
    ): FlightRepository = FlightRepositoryImpl(api, firestore)

}
