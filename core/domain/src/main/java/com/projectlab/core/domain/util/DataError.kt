package com.projectlab.core.domain.util

sealed interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }

    enum class FirebaseAuth : DataError {
        INVALID_CREDENTIAL,
        TOO_MANY_REQUESTS,
        UNAUTHORIZED,
        NO_INTERNET,
        INTERNAL_ERROR,
        EMAIL_ALREADY_IN_USE,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL
    }
}