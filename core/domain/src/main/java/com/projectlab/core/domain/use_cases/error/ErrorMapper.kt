package com.projectlab.core.domain.use_cases.error

import com.projectlab.core.domain.util.DataError

interface ErrorMapper {
    fun map(error: DataError.Network): String
}