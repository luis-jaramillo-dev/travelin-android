package com.projectlab.core.domain.use_cases.hotels

data class HotelsUseCases(

    val getHotelsByCity: GetHotelsByCityUseCase,
    val favoriteHotel: FavoriteHotelUseCase,
    val unfavoriteHotel: UnfavoriteHotelUseCase

)

