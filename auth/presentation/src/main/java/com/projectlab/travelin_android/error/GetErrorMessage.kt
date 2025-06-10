package com.projectlab.travelin_android.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.projectlab.auth.presentation.R
import com.projectlab.core.domain.util.DataError

@Composable
fun getErrorMessage(error: DataError.FirebaseAuth?): String {
    return when (error) {
        DataError.FirebaseAuth.INVALID_CREDENTIAL -> {
            stringResource(R.string.wrong_password)
        }
        DataError.FirebaseAuth.TOO_MANY_REQUESTS -> {
            stringResource(R.string.too_many_requests)
        }
        DataError.FirebaseAuth.UNAUTHORIZED -> {
            stringResource(R.string.unauthorized)
        }
        DataError.FirebaseAuth.NO_INTERNET -> {
            stringResource(R.string.no_internet_connection)
        }
        DataError.FirebaseAuth.INTERNAL_ERROR -> {
            stringResource(R.string.internal_error)
        }
        DataError.FirebaseAuth.EMAIL_ALREADY_IN_USE -> {
            stringResource(R.string.account_already_exists)
        }
        DataError.FirebaseAuth.UNKNOWN, null -> {
            stringResource(R.string.unknown_error)
        }
    }
}
