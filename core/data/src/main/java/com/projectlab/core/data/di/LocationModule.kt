package com.projectlab.core.data.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.projectlab.core.data.service.GeocoderService
import com.projectlab.core.data.usecase.GetCityFromCoordinatesUseCaseImpl
import com.projectlab.core.data.usecase.GetCoordinatesFromCityUseCaseImpl
import com.projectlab.core.data.utils.LocationUtils
import com.projectlab.core.domain.repository.LocationRepository
import com.projectlab.core.domain.use_cases.location.GetCityFromCoordinatesUseCase
import com.projectlab.core.domain.use_cases.location.GetCoordinatesFromCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    fun provideGeocoder(@ApplicationContext context: Context): Geocoder {
        return Geocoder(context)
    }

    @Provides
    fun provideLocationRepository(
        @ApplicationContext context: Context,
        geocoderService: GeocoderService,
        fusedLocationClient: FusedLocationProviderClient
    ) : LocationRepository {
        return LocationUtils(context, geocoderService, fusedLocationClient)
    }

    @Provides
    fun provideGetCityFromCoordinatesUseCase(
        impl: GetCityFromCoordinatesUseCaseImpl
    ): GetCityFromCoordinatesUseCase = impl

    @Provides
    fun provideGetCoordinatesFromCityUseCase(
        impl: GetCoordinatesFromCityUseCaseImpl
    ): GetCoordinatesFromCityUseCase = impl

}