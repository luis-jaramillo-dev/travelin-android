package com.projectlab.core.domain.repository

import com.projectlab.core.domain.model.Response
import com.projectlab.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun createUser(user: User): Response<Boolean>
    fun getUserById(id: String): Flow<User>
}