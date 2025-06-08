package com.projectlab.core.presentation.ui.utils

import android.content.Context
import com.projectlab.core.domain.use_cases.error.ErrorMapper
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.presentation.designsystem.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ErrorMapperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ErrorMapper {
    override fun map(error: DataError.Network): String {
        return when (error) {
            DataError.Network.NO_INTERNET -> context.getString(R.string.error_no_internet)
            DataError.Network.UNAUTHORIZED -> context.getString(R.string.error_unauthorized)
            DataError.Network.REQUEST_TIMEOUT -> context.getString(R.string.error_timeout)
            DataError.Network.TOO_MANY_REQUESTS -> context.getString(R.string.error_too_many_requests)
            DataError.Network.CONFLICT -> context.getString(R.string.error_conflict)
            DataError.Network.PAYLOAD_TOO_LARGE -> context.getString(R.string.error_payload_too_large)
            DataError.Network.SERVER_ERROR -> context.getString(R.string.error_server)
            DataError.Network.SERIALIZATION -> context.getString(R.string.error_serialization)
            DataError.Network.UNKNOWN -> context.getString(R.string.error_unknown)
        }
    }
}