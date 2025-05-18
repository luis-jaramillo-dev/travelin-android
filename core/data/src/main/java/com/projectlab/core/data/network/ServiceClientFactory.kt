package com.projectlab.core.data.network

import javax.inject.Inject
import javax.inject.Named
import okhttp3.OkHttpClient

/**
 * Factory for creating OkHttpClient instances based on the selected provider.
 * @param amadeusFactory Factory for creating Amadeus client.
 * @param liteApiFactory Factory for creating LiteAPI client (still needs to be implemented).
 *
 * @author ricardoceadev
 */

class ServiceClientFactory @Inject constructor(
    @Named("amadeus") private val amadeusFactory: HttpClientFactory,
    @Named("liteapi") private val liteApiFactory: HttpClientFactory,
) : HttpClientFactory{

    enum class Provider {
        AMADEUS,
        LITEAPI
    }

    private var current : Provider = Provider.AMADEUS

    fun switch(provider: Provider) {
        current = provider
    }

    override fun createClient(): OkHttpClient = when (current) {
        Provider.AMADEUS -> amadeusFactory.createClient()
        Provider.LITEAPI -> liteApiFactory.createClient()
    }

}