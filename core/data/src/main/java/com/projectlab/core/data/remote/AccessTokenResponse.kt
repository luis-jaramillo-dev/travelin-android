package com.projectlab.core.data.remote

import com.google.gson.annotations.SerializedName

/**
 * AccessTokenResponse is a data class that represents the response from the Amadeus API
 * when requesting an access token. It contains the access token, token type, and expiration time.
 *
 * @property accessToken The access token received from the API.
 * @property tokenType The type of the token.
 * @property expiresIn The time in seconds until the token expires.
 */

data class AccessTokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: Int
)
