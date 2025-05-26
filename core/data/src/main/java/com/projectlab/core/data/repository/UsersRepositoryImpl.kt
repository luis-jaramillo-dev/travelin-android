package com.projectlab.core.data.repository

import com.google.firebase.firestore.CollectionReference
import com.projectlab.core.domain.model.Response
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.model.User
import com.projectlab.core.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val usersRef: CollectionReference) :
    UsersRepository {

    override suspend fun createUser(user: User): Response<Boolean> {
        return try {
            usersRef.document(user.id).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow {
        val snapshotListener = usersRef.document(id).addSnapshotListener { snapshot, e ->
            val user = snapshot?.toObject(User::class.java) ?: User(
                id = "",
                email = "",
                age = "",
                firstName = "",
                lastName = "",
                countryCode = "",
                phoneNumber = "",
            )
            trySend(user)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }

}