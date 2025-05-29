package com.projectlab.core.data.network

import android.util.Log
import androidx.datastore.core.DataStore
import com.projectlab.core.data.config.Config
import com.projectlab.core.data.remote.AmadeusApiService
import com.projectlab.core.domain.proto.AmadeusToken
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import com.projectlab.core.domain.repository.TokenProvider

/**
 * TokenAuthenticator is an OkHttp Authenticator that detects when the server responds
 * with a 401 Unauthorized (expired or invalid token) and reactively takes care of:
 * 1) Requesting a new access token from the Amadeus API.
 * 2) Saving the new token in the DataStore (and cache).
 * 3) Retrying the original request with the updated token.
 *
 * @param tokenProvider The TokenProvider instance used to retrieve the access token.
 * @param amadeusApiService The AmadeusApiService instance used to request a new access token.
 * @param amadeusTokenStore The DataStore instance used to store the Amadeus token.
 */

class AmadeusTokenAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val amadeusApiService: AmadeusApiService,
    private val amadeusTokenStore: DataStore<AmadeusToken>
) : Authenticator {

    private val TAG = "TokenAuthenticator" // logcat tag

    override fun authenticate(route: Route?, response: Response): Request? {
        // logcat message TODO: erase this one before production release
        Log.d(TAG, "🚨 authenticate() Received 401 for ${response.request.url}, retrying...")

        // To avoid infinite loops, we check if the response has already been handled
        if (response.request.header("Authorization") != null &&
            responseCount(response) >= 2) {
            // logcat message TODO: erase this one before production release
            Log.w(TAG, "❌ Already retrying twice, aborting retry")
            return null
        }

        // We try to retrieve a new token from the TokenProvider, if it is empty or has expired,
        // we will request a new AccessToken from the Amadeus API.
        val current = runBlocking {
            // logcat message TODO: erase this one before production release
            Log.d(TAG, "🙋‍♂️ Requesting a new token from the TokenProvider...")
            tokenProvider.getAccessToken()
        }
        // logcat message TODO: erase this one before production release
        Log.d(TAG, "🔄 token before refresh: ${current.ifEmpty { "<EMPTY>" }}")

        // If the token is empty, we will request a new AccessToken from the Amadeus API.
        val newTokenResponse = runBlocking {
            // logcat message TODO: erase this one before production release
            Log.d(TAG, "🌐 We request a new AccessToken from the Amadeus API...")
            amadeusApiService.getAccessToken(
                clientId = Config.apiKey,
                clientSecret = Config.apiSecret
            )
        }
        // logcat message TODO: erase this one before production release
        Log.d(TAG, "➡️ Refresh received: token='${newTokenResponse.accessToken}', " +
                "expiresIn=${newTokenResponse.expiresIn}") // expiresIn is in seconds

        // We calculate expiration time, save the new token in the DataStore
        // and update the internal cache.
        val expirationEpoch = System.currentTimeMillis() / 1000 + newTokenResponse.expiresIn
        runBlocking {
            // logcat message TODO: erase this one before production release
            Log.d(TAG, "💾 Save new token into DataStorage, with expiration in $expirationEpoch")
            amadeusTokenStore.updateData { proto ->
                proto.toBuilder()
                    .setAccessToken(newTokenResponse.accessToken)
                    .setExpiration(expirationEpoch)
                    .build()
            }
        }

        // We construct a new request with the new token and return it.
        val newRequest = response.request.newBuilder()
            .header("Authorization", "Bearer ${newTokenResponse.accessToken}")
            .build()
        // logcat message TODO: erase this one before production release
        Log.d(TAG, "🔄 Retrying original request with new token")
        return newRequest

    }

    // This function counts the number of times a response has been retried.
    private fun responseCount(response: Response): Int {
        var res = response.priorResponse
        var count = 1
        while (res?.priorResponse != null) {
            count++
            res = res.priorResponse
        }
        return count
    }
}