package com.projectlab.core.data.remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * AmadeusApiService is an interface that defines the API endpoints for the Amadeus API.
 * It includes a method to get an access token using client credentials.
 */

interface AmadeusApiService {
    @FormUrlEncoded
    @POST("v1/security/oauth2/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): AccessTokenResponse
}