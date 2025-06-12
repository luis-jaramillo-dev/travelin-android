package com.projectlab.core.domain.use_cases.hotels

data class HotelsUseCases(

    val getHotelsByCity: GetHotelsByCityUseCase,
    val getHotelsByCoordinates: GetHotelsByCoordinatesUseCase,
    val favoriteHotel: FavoriteHotelUseCase,
    val unfavoriteHotel: UnfavoriteHotelUseCase,
    val saveFavoriteHotelUseCase: SaveFavoriteHotelUseCase,
    val getFavoriteHotelsUseCase: GetFavoriteHotelsUseCase,
    val removeFavoriteHotelUseCase: RemoveFavoriteHotelUseCase,
)

