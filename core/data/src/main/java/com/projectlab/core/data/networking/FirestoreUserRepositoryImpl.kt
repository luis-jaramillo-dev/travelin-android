package com.projectlab.core.data.networking

import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreUserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {

    private val usersCol = firestore.collection("users")

    override suspend fun createUser(user: UserEntity) : Result<String> = runCatching {
        val docRef = usersCol.document()
        user.email.let {
            // podrías usarlo como ID: usersCol.document(it)
        }
        docRef.set(user).await()           // usa kotlinx-coroutines-play-services
        docRef.id
    }

    override suspend fun getUserById(id: String): UserEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: UserEntity): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: String): Boolean {
        TODO("Not yet implemented")
    }
}