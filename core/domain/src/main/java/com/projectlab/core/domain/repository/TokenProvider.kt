package com.projectlab.core.domain.repository

/**
 * Interface for providing access tokens.
 * This interface is used to retrieve and cache access tokens for API requests.
 * getAccessToken() is a suspend function that retrieves a new access token,
 * getCachedToken() retrieves the cached token if it is still valid.
 */

interface TokenProvider {
    suspend fun getAccessToken(): String
    fun getCachedToken(): String?
}