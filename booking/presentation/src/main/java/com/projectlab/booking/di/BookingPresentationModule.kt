package com.projectlab.booking.presentation.di

import com.projectlab.core.domain.repository.HotelsRepository
import com.projectlab.core.domain.use_cases.hotels.GetHotelsByCityUseCase
import com.projectlab.core.domain.use_cases.hotels.HotelsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object BookingPresentationModule {
    @Provides
    fun provideHotelsUseCases(repository: HotelsRepository) = HotelsUseCases(
        getHotelsByCity = GetHotelsByCityUseCase(repository),
    )
}