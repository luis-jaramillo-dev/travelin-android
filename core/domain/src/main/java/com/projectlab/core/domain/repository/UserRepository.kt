package com.projectlab.core.domain.repository

interface UserRepository {
    suspend fun createUser(user: UserEntity): Result<String>
    suspend fun getUserById(id: String): UserEntity?
    suspend fun updateUser(user: UserEntity): Boolean
    suspend fun deleteUser(id: String): Boolean
}