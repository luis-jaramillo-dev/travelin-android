package api

import dto.FlightOffersResponseDto
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
}