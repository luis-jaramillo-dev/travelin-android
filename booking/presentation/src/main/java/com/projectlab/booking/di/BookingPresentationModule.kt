package com.projectlab.booking.presentation.di

import com.projectlab.core.domain.repository.HotelsRepository
import com.projectlab.core.domain.repository.UsersRepository
import com.projectlab.core.domain.use_cases.hotels.FavoriteHotelUseCase
import com.projectlab.core.domain.use_cases.hotels.GetHotelsByCityUseCase
import com.projectlab.core.domain.use_cases.hotels.GetHotelsByCoordinatesUseCase
import com.projectlab.core.domain.use_cases.hotels.HotelsUseCases
import com.projectlab.core.domain.use_cases.hotels.UnfavoriteHotelUseCase
import com.projectlab.core.domain.use_cases.users.UsersUseCases
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
        favoriteHotel = FavoriteHotelUseCase(repository),
        unfavoriteHotel = UnfavoriteHotelUseCase(repository),
        getHotelsByCoordinates = GetHotelsByCoordinatesUseCase(repository),
    )
}