package com.projectlab.core.domain.mapper

import com.projectlab.core.domain.entity.FavoriteHotelEntity
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.model.HotelLocation
import com.projectlab.core.domain.model.HotelRating

fun Hotel.toFavoriteHotelEntity(): FavoriteHotelEntity {
    return FavoriteHotelEntity(
        id = this.id,
        name = this.name,
        description = "",
        location = this.location.toString(),
        rating = this.rating.overallRating.toDouble(),
        displayImageUrl = this.displayImageUrl,
        amenities = this.amenities ?: emptyList(),
        address = this.location.toString(),
        priceRange = this.displayPrice,
        latitude = this.location.latitude,
        longitude = this.location.longitude
    )
}

fun FavoriteHotelEntity.toHotel(): Hotel {
    return Hotel(
        id = this.id,
        name = this.name,
        location = HotelLocation(this.longitude, this.latitude, "", "", this.location),
        rating = HotelRating(0, this.rating.toInt(), 0),
        displayImageUrl = this.displayImageUrl,
        amenities = this.amenities,
        displayPrice = this.priceRange,
        phoneNumber = "",
        hotelOffers = null,
    )
}