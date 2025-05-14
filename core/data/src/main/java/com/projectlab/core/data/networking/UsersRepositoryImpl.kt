package com.projectlab.core.data.networking

import com.google.firebase.firestore.CollectionReference
import com.projectlab.core.domain.model.Response
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val usersRef: CollectionReference) :
    UsersRepository {

    override suspend fun create(userEntity: UserEntity): Response<Boolean> {
        return try {
            // TODO: check if we use the EntityId or not
            // usersRef.document(userEntity.id?.value ?: "").set(userEntity).await()
            usersRef.document(userEntity.id).set(userEntity).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<UserEntity> = callbackFlow {
        val snapshotListener = usersRef.document(id).addSnapshotListener { snapshot, e ->
            val userEntity = snapshot?.toObject(UserEntity::class.java) ?: UserEntity(
                //id = EntityId(""), TODO: check if we use the EntityId or not
                id = "",
                email = "",
                age = "",
                firstName = "",
                lastName = "",
                countryCode = "",
                phoneNumber = "",
            )
            trySend(userEntity)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }

}