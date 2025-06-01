package com.projectlab.booking.presentation.fakes

import android.content.Context
import com.projectlab.core.domain.use_cases.error.ErrorMapper
import com.projectlab.core.domain.util.DataError

class FakeErrorMapper : ErrorMapper {
    override fun map(error: DataError.Network): String {
        return when (error) {
            DataError.Network.NO_INTERNET -> "No internet"
            else -> "Generic error"
        }
    }
}