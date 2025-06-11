package com.projectlab.core.data.remote.hotels

import com.projectlab.core.data.remote.hotels.responses.HotelOffersResponse
import com.projectlab.core.data.remote.hotels.responses.HotelRatingResponse
import com.projectlab.core.data.remote.hotels.responses.HotelSearchByIdResponse
import com.projectlab.core.data.remote.hotels.responses.HotelsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelsApiService {
    @GET("v1/reference-data/locations/hotels/by-city")
    suspend fun getHotelsByCity(
        @Query("cityCode") cityCode: String,
        @Query("ratings") ratings: String
    ): HotelsSearchResponse

    @GET("v1/reference-data/locations/hotels/by-geocode")
    suspend fun getHotelsByCoordinates(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("ratings") ratings: String
    ): HotelsSearchResponse

    @GET("v2/e-reputation/hotel-sentiments")
    suspend fun getHotelRatingById(
        @Query("hotelIds") hotelIds: String,
    ): HotelRatingResponse

    @GET("v3/shopping/hotel-offers")
    suspend fun getHotelOffers(
        @Query("hotelIds") hotelIds: String,
    ): HotelOffersResponse

    @GET("v1/reference-data/locations/hotels/by-hotels")
    suspend fun getHotelById(
        @Query("hotelIds") hotelIds: String,
    ): HotelSearchByIdResponse
}