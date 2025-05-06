package com.projectlab.core.data.config

import com.projectlab.core.data.BuildConfig

object Config {
    val apiKey: String get() = BuildConfig.API_KEY
    val apiSecret: String get() = BuildConfig.API_SECRET
    val baseUrl: String get() = BuildConfig.BASE_URL
}