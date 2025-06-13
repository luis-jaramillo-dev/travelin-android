package com.projectlab.core.domain.use_cases.itineraries

data class ItinerariesUseCases(
    val getItinerariesByUser: GetItinerariesByUserUseCase,
    val createItinerary: CreateItineraryUseCase,
    val addHotelToItinerary: AddHotelToItineraryUseCase
)