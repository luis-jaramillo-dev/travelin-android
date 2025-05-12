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
        @Query("departureDate") date: String,
        @Query("adults") adults: Int,
        @Query("max") max: Int
    ): FlightOffersResponseDto

    @GET("v1/reference-data/locations")
    suspend fun getLocations(
        @Query("keyword") keyword: String,
        @Query("subType") subType: String = "AIRPORT,CITY"
    ): LocationResponse
}