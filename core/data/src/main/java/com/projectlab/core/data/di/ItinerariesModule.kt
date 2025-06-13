package com.projectlab.core.data.di

import com.projectlab.core.data.usecase.AddActivityToItineraryUseCaseImpl
import com.projectlab.core.data.usecase.GetAllItinerariesUseCaseImpl
import com.projectlab.core.domain.use_cases.itineraries.AddActivityToItineraryUseCase
import com.projectlab.core.domain.use_cases.itineraries.GetAllItinerariesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ItinerariesModule {

//    @Provides
//    fun provideItineraryRepository(
//        itineraryRepositoryImpl: ItineraryRepositoryImpl
//    ): ItineraryRepository {
//        return itineraryRepositoryImpl
//    }

    @Provides
    fun provideGetAllItinerariesUseCase(
        impl: GetAllItinerariesUseCaseImpl,
    ): GetAllItinerariesUseCase = impl

    @Provides
    fun provideAddActivityToItineraryUseCase(
        impl: AddActivityToItineraryUseCaseImpl,
    ): AddActivityToItineraryUseCase = impl



}