package com.projectlab.core.domain.repository

interface TokenProvider {
    suspend fun getAccessToken(): String
    fun getCachedToken(): String?
}