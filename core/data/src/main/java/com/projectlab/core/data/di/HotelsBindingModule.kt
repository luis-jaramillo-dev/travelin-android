package com.projectlab.core.data.di

import com.projectlab.core.domain.use_cases.hotels.GetHotelsByCoordinatesUseCase
import com.projectlab.core.domain.use_cases.hotels.GetHotelsByCoordinatesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HotelsBindingModule {

    @Binds
    abstract fun GetHotelsByCoordinatesUseCase(
        impl: GetHotelsByCoordinatesUseCaseImpl
    ): GetHotelsByCoordinatesUseCase

}