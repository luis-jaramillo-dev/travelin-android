package com.projectlab.auth.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.projectlab.core.domain.model.Response

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun register(email: String, password: String): Response<FirebaseUser>
    fun logout()

}