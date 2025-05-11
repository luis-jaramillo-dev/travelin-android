package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.UserEntity


interface UserRepository {
    suspend fun createUser(user: UserEntity): Result<String>
    suspend fun getUserById(id: String): UserEntity?
}