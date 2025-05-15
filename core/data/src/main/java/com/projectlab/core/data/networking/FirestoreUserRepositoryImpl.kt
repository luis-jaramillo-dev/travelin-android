package com.projectlab.core.data.networking



import kotlinx.coroutines.tasks.await
import javax.inject.Inject

import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.repository.UserRepository
import com.projectlab.core.data.model.dto.FirestoreUserDTO
import kotlinx.coroutines.flow.Flow

/**
 * FirestoreUserRepositoryImpl is a concrete implementation of the UserRepository interface.
 * It uses Firebase Firestore to perform CRUD operations on user data.
 *
 * @param firestore The FirebaseFirestore instance used to interact with Firestore.
 */

class FirestoreUserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {

    private var userCol = firestore.collection("Users")

    override suspend fun createUser(user: UserEntity): Result<EntityId> = runCatching {
        // create dto:
        val dto = FirestoreUserDTO.fromDomain(user)
        // add to firestore:
        val docRef = userCol.document()
        docRef.set(dto).await()
        // return the id:
        EntityId(docRef.id)
    }

    override suspend fun getUserById(id: String): Flow<UserEntity?> {
        TODO("Not yet implemented")
    }
}