package com.projectlab.travelin_android.api

import com.projectlab.travelin_android.dto.FlightOffersResponseDto
import com.projectlab.travelin_android.dto.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightApiService {
    @GET("v2/shopping/flight-offers")
    suspend fun getFlightOffers(
        @Query("originLocationCode") origin: String,
        @Query("destinationLocationCode") destination: String,
        @Query("departureDate") departureDate: String,
        @Query("returnDate") returnDate: String? = null,
        @Query("adults") adults: Int = 1,
        @Query("children") children: Int = 0,
        @Query("infants") infants: Int = 0,
        @Query("travelClass") travelClass: String? = null,
        @Query("nonStop") nonStop: Boolean = false,
        @Query("maxPrice") maxPrice: Int? = null,
        @Query("max") max: Int = 250
    ): FlightOffersResponseDto

    @GET("v1/reference-data/locations")
    suspend fun getLocations(
        @Query("keyword") keyword: String,
        @Query("subType") subType: String = "AIRPORT,CITY"
    ): LocationResponse
}