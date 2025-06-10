package com.projectlab.travelin_android

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.projectlab.auth.domain.repository.AuthRepository
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import com.projectlab.travelin_android.networking.authResponseToResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {
    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    override suspend fun login(
        email: String,
        password: String,
    ): Result<FirebaseUser, DataError.FirebaseAuth> {
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            return Result.Success(result.user!!)
        } catch (e: FirebaseAuthException) {
            return authResponseToResult(e)
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(DataError.FirebaseAuth.UNKNOWN)
        }
    }

    override suspend fun register(
        email: String,
        password: String,
    ): Result<FirebaseUser, DataError.FirebaseAuth> {
        try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            return Result.Success(result.user!!)
        } catch (e: FirebaseAuthException) {
            return authResponseToResult(e)
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(DataError.FirebaseAuth.UNKNOWN)
        }
    }

    override fun logout(): Result<Unit, DataError.FirebaseAuth> {
        try {
            firebaseAuth.signOut()
            return Result.Success(Unit)
        } catch (e: FirebaseAuthException) {
            return authResponseToResult(e)
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(DataError.FirebaseAuth.UNKNOWN)
        }
    }
}
