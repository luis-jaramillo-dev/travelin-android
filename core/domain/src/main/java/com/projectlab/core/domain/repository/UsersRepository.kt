package com.projectlab.core.domain.repository

import com.projectlab.core.domain.model.Response
import com.projectlab.core.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun create(userEntity: UserEntity): Response<Boolean>
    fun getUserById(id: String): Flow<UserEntity>
}