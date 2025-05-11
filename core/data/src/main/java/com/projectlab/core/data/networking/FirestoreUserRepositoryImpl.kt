package com.projectlab.core.data.networking



import kotlinx.coroutines.tasks.await
import javax.inject.Inject

import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.repository.UserRepository
import com.projectlab.core.data.model.dto.UserDTO
import kotlinx.coroutines.tasks.await

class FirestoreUserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {

    private val userCol = firestore.collection("users")

    override suspend fun createUser(user: UserEntity): Result<EntityId> = runCatching {
        // create dto:
        val dto = UserDTO.fromDomain(user)
        // add to firestore:
        val docRef = userCol.document()
        docRef.set(dto).await()
        // return the id:
        EntityId(docRef.id)
    }

    override suspend fun getUserById(id: String): UserEntity? {
        TODO("Not yet implemented")
    }
}