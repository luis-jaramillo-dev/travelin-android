package com.projectlab.core.domain.model

data class Hotel(
    var id: String,
    val name: String,
    val location: HotelLocation,
    val rating: HotelRating,
    val displayPrice: String,
    var isFavourite: Boolean = false,
    val displayImageUrl: String,
    val hotelOffers: List<HotelOffer>?,
    val amenities: List<String>? = emptyList(),
    val photoUrls: List<String> = emptyList(),
)

data class HotelLocation(
    val longitude: Double,
    val latitude: Double,
    val country: String,
    val city: String,
    val address: String
)

data class HotelRating(
    val stars: Int?,
    val overallRating: Int,
    val numberOfReviews: Int,
)

data class HotelOffer(
    val id: String,
    val price: Price,
    val checkInDate: String,
    val checkOutDate: String,
    val room: HotelRoom
)

data class HotelRoom(
    val category: String,
    val bedType: String,
    val beds: Int,
    val bathrooms: Int,
    val description: String,
    val adults: Int,
    val childAges: Int,
    val squareMeters: Int
)