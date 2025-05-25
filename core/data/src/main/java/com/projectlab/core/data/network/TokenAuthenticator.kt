package com.projectlab.core.data.network

import android.util.Log
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import com.projectlab.core.domain.repository.TokenProvider

/**
 * TokenAuthenticator is an OkHttp Authenticator that handles 401 Unauthorized responses.
 * OkHttp Authenticator that, upon receiving a 401, forces the retrieval of a new token
 * and retry the original request with the renewed Authorization header.
 *
 * @param tokenProvider The TokenProvider instance used to retrieve the access token.
 */

class TokenAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider
) : Authenticator {

    private val TAG = "TokenAuthenticator" // logcat tag

    override fun authenticate(route: Route?, response: Response): Request? {

        // Log.d(TAG, "🚨 authenticate() Received 401, retrying...") // logcat message TODO: erase this one before production release

        // To avoid infinite loops, we check if the response has already been handled
        if (response.request.header("Authorization") != null &&
            responseCount(response) >= 2) {
            // Log.w(TAG, "It has already been retried before, aborting retry") // logcat message TODO: erase this one before production release
            return null
        }

        // We force retrieve a new token from the TokenProvider
        val newToken = runBlocking {
            // Log.d(TAG, "Requesting a new token from the TokenProvider") // logcat message TODO: erase this one before production release
            tokenProvider.getAccessToken()
        }

        // Log.d(TAG, "🔄 New token received: $newToken") // logcat message TODO: erase this one before production release

        return response.request
            .newBuilder()
            .header("Authorization", "Bearer $newToken")
            .build()
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