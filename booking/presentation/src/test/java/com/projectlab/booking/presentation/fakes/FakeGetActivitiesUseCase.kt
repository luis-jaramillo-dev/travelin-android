package com.projectlab.booking.presentation.fakes

import com.projectlab.core.data.usecase.GetActivitiesUseCase
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import org.mockito.Mockito.mock

class FakeGetActivitiesUseCase : GetActivitiesUseCase(mock(), mock()) {

    var result: Result<List<Activity>, DataError.Network> =
        Result.Success(emptyList())

    override suspend fun invoke(
        latitude: Double,
        longitude: Double
    ): Result<List<Activity>, DataError.Network> {
        return result
    }
}