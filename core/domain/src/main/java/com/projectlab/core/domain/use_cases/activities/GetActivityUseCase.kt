package com.projectlab.core.domain.use_cases.activities

import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

fun interface GetActivityUseCase {
    suspend operator fun invoke(id: String): Result<Activity, DataError.Network>
}