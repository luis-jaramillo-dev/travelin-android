package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun createUser(user: UserEntity): Result<EntityId>
    suspend fun getUserById(id: String): Flow<UserEntity?>
}