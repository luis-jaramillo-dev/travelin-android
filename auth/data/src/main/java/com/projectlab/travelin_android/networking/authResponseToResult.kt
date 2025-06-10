package com.projectlab.travelin_android.networking

import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuthException
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

private object FirebaseErrorNames {
    private val errorCodeToNameMap: Map<Int, String> by lazy {
        buildErrorCodeMap()
    }

    private fun buildErrorCodeMap(): Map<Int, String> {
        val map = mutableMapOf<Int, String>()
        try {
            // maps the error code (int) to it's name in the FirebaseError enum
            FirebaseError::class.java.declaredFields.forEach { field ->
                if (field.name.startsWith("ERROR_") && field.type == Int::class.javaPrimitiveType) {
                    field.isAccessible = true
                    val errorCode = field.getInt(null)
                    map[errorCode] = field.name
                }
            }
        } catch (_: Exception) {
            // empty map if failure
        }
        return map
    }

    fun get(errorCode: Int): String? = errorCodeToNameMap[errorCode]
}

fun <T> authResponseToResult(
    response: FirebaseAuthException,
): Result<T, DataError.FirebaseAuth> {
    // FirebaseAuth API returns the error name, not the error code
    // must map from error code to error name to correctly handle them
    return when (response.errorCode) {
        FirebaseErrorNames.get(FirebaseError.ERROR_EMAIL_ALREADY_IN_USE) -> {
            Result.Error(DataError.FirebaseAuth.EMAIL_ALREADY_IN_USE)
        }

        FirebaseErrorNames.get(FirebaseError.ERROR_INVALID_CREDENTIAL),
        FirebaseErrorNames.get(FirebaseError.ERROR_WRONG_PASSWORD),
        FirebaseErrorNames.get(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL),
            -> {
            Result.Error(DataError.FirebaseAuth.INVALID_CREDENTIAL)
        }

        FirebaseErrorNames.get(FirebaseError.ERROR_TOO_MANY_REQUESTS) -> {
            Result.Error(DataError.FirebaseAuth.TOO_MANY_REQUESTS)
        }

        FirebaseErrorNames.get(FirebaseError.ERROR_USER_DISABLED) -> {
            Result.Error(DataError.FirebaseAuth.UNAUTHORIZED)
        }

        FirebaseErrorNames.get(FirebaseError.ERROR_NETWORK_REQUEST_FAILED) -> {
            Result.Error(DataError.FirebaseAuth.NO_INTERNET)
        }

        else -> {
            Result.Error(DataError.FirebaseAuth.UNKNOWN)
        }
    }
}
