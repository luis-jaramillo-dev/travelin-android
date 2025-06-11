package com.projectlab.auth.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun login(
        email: String,
        password: String,
    ): Result<FirebaseUser, DataError.FirebaseAuth>

    suspend fun register(
        email: String,
        password: String,
    ): Result<FirebaseUser, DataError.FirebaseAuth>

    fun logout(): Result<Unit, DataError.FirebaseAuth>
}
