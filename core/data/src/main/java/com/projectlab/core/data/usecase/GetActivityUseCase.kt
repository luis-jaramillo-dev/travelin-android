package com.projectlab.core.data.usecase

import com.projectlab.core.data.mapper.toDomain
import com.projectlab.core.data.remote.ActivityApiService
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val api: ActivityApiService,
) {
    suspend operator fun invoke(id: String): Result<Activity, DataError.Network> {
        return try {
            val response = api.getActivityById(id)
            Result.Success(response.data.toDomain())
        } catch (e: Exception) {
            val networkError = when (e) {
                is UnknownHostException -> DataError.Network.NO_INTERNET
                is SocketTimeoutException -> DataError.Network.REQUEST_TIMEOUT
                is retrofit2.HttpException -> {
                    when (e.code()) {
                        401 -> DataError.Network.UNAUTHORIZED
                        500 -> DataError.Network.SERVER_ERROR
                        else -> DataError.Network.UNKNOWN
                    }
                }
                else -> DataError.Network.UNKNOWN
            }
            Result.Error(networkError)
        }
    }
}