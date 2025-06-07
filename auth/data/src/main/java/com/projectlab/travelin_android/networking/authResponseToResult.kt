package com.projectlab.travelin_android.networking

import com.google.firebase.FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL
import com.google.firebase.FirebaseError.ERROR_EMAIL_ALREADY_IN_USE
import com.google.firebase.FirebaseError.ERROR_INVALID_CREDENTIAL
import com.google.firebase.FirebaseError.ERROR_NETWORK_REQUEST_FAILED
import com.google.firebase.FirebaseError.ERROR_TOO_MANY_REQUESTS
import com.google.firebase.FirebaseError.ERROR_USER_DISABLED
import com.google.firebase.FirebaseError.ERROR_WRONG_PASSWORD
import com.google.firebase.auth.FirebaseAuthException
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

inline fun <reified T> authResponseToResult(response: FirebaseAuthException): Result<T, DataError.FirebaseAuth> {
    return when (response.errorCode.toInt()) {
        ERROR_EMAIL_ALREADY_IN_USE -> Result.Error(DataError.FirebaseAuth.EMAIL_ALREADY_IN_USE)
        ERROR_INVALID_CREDENTIAL -> Result.Error(DataError.FirebaseAuth.INVALID_CREDENTIAL)
        ERROR_WRONG_PASSWORD -> Result.Error(DataError.FirebaseAuth.INVALID_CREDENTIAL)
        ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL -> Result.Error(DataError.FirebaseAuth.INVALID_CREDENTIAL)
        ERROR_TOO_MANY_REQUESTS -> Result.Error(DataError.FirebaseAuth.TOO_MANY_REQUESTS)
        ERROR_USER_DISABLED -> Result.Error(DataError.FirebaseAuth.UNAUTHORIZED)
        ERROR_NETWORK_REQUEST_FAILED -> Result.Error(DataError.FirebaseAuth.NO_INTERNET)
        in 17000..17499 -> Result.Error(DataError.FirebaseAuth.INTERNAL_ERROR)
        else -> Result.Error(DataError.FirebaseAuth.UNKNOWN)
    } as Result<T, DataError.FirebaseAuth>
}