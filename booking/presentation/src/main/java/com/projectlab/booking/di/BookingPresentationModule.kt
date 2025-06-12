package com.projectlab.booking.presentation.di

import com.projectlab.core.data.usecase.GetFavoriteHotelsUseCaseImpl
import com.projectlab.core.data.usecase.RemoveFavoriteHotelUseCaseImpl
import com.projectlab.core.data.usecase.SaveFavoriteHotelUseCaseImpl
import com.projectlab.core.domain.repository.HotelsRepository
import com.projectlab.core.domain.use_cases.hotels.FavoriteHotelUseCase
import com.projectlab.core.domain.use_cases.hotels.GetFavoriteHotelsUseCase
import com.projectlab.core.domain.use_cases.hotels.GetHotelsByCityUseCase
import com.projectlab.core.domain.use_cases.hotels.GetHotelsByCoordinatesUseCaseImpl
import com.projectlab.core.domain.use_cases.hotels.HotelsUseCases
import com.projectlab.core.domain.use_cases.hotels.RemoveFavoriteHotelUseCase
import com.projectlab.core.domain.use_cases.hotels.SaveFavoriteHotelUseCase
import com.projectlab.core.domain.use_cases.hotels.UnfavoriteHotelUseCase
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
        getHotelsByCoordinates = GetHotelsByCoordinatesUseCaseImpl(repository),
        saveFavoriteHotelUseCase = SaveFavoriteHotelUseCaseImpl(repository),
        getFavoriteHotelsUseCase = GetFavoriteHotelsUseCaseImpl(repository),
        removeFavoriteHotelUseCase = RemoveFavoriteHotelUseCaseImpl(repository)
    )

    @Provides
    fun provideRemoveFavoriteHotelUseCase(
        repository: HotelsRepository
    ): RemoveFavoriteHotelUseCase {
        return RemoveFavoriteHotelUseCaseImpl(repository)
    }

    @Provides
    fun provideSaveFavoriteHotelUseCase(
        repository: HotelsRepository
    ): SaveFavoriteHotelUseCase {
        return SaveFavoriteHotelUseCaseImpl(repository)
    }

    @Provides
    fun provideGetFavoriteHotelsUseCase(
        repository: HotelsRepository
    ): GetFavoriteHotelsUseCase {
        return GetFavoriteHotelsUseCaseImpl(repository)
    }
}