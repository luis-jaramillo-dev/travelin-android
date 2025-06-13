package com.projectlab.core.data.di

import com.projectlab.core.data.repository.ItineraryRepositoryImpl
import com.projectlab.core.data.usecase.GetAllItinerariesUseCaseImpl
import com.projectlab.core.data.usecase.QueryFavoriteActivitiesUseCaseImpl
import com.projectlab.core.domain.repository.ItineraryRepository
import com.projectlab.core.domain.use_cases.activities.QueryFavoriteActivitiesUseCase
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
    fun getAllItinerariesUseCase(
        impl: GetAllItinerariesUseCaseImpl,
    ): GetAllItinerariesUseCase = impl



}