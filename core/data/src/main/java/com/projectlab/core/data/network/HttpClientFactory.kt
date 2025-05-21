package com.projectlab.core.data.network

import okhttp3.OkHttpClient

/**
 * Factory interface for creating OkHttpClient instances.
 *
 * This interface allows for the creation of OkHttpClient instances with custom configurations.
 * Implementations of this interface can provide different configurations
 * based on the needs of the application.
 *
 * @author ricardoceadev
 */


interface HttpClientFactory {
    fun createClient(): OkHttpClient
}