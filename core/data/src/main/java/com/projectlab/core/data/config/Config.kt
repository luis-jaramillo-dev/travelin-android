package com.projectlab.core.data.config

object Config {
    val apiKey: String
    val apiSecret: String
    val baseUrl: String

    init {
        val properties = java.util.Properties().apply {
            val file = java.io.File("local.properties")
            if (file.exists()) {
                load(file.inputStream())
            } else {
                error("local.properties not found")
            }
        }

        apiKey = properties["API_KEY"]?.toString() ?: error("API_KEY missing")
        apiSecret = properties["API_SECRET"]?.toString() ?: error("API_SECRET missing")
        baseUrl = properties["BASE_URL"]?.toString() ?: error("BASE_URL missing")
    }
}