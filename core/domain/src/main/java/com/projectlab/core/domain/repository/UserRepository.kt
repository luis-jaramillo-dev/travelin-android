package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow

/**
 * UserRepository interface for user-related operations.
 *
 * This interface defines the contract for user-related data operations, including creating a user
 * and retrieving a user by their ID.
 *
 * @author ricardoceadev
 */


interface UserRepository {
    suspend fun createUser(user: UserEntity): Result<EntityId>
    suspend fun getUserById(id: String): Flow<UserEntity?>
}