package com.projectlab.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.projectlab.core.domain.proto.UserSession
import com.projectlab.core.domain.repository.UserSessionProvider
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first

class UserSessionProviderImpl @Inject constructor(
    private val userSessionStore: DataStore<UserSession>,
) : UserSessionProvider {
    override suspend fun getUserSessionId(): String? {
        val userSession = try {
            userSessionStore.data.first()
        } catch (_: IOException) {
            UserSession.getDefaultInstance()
        }

        return userSession.userId?.ifEmpty { null }
    }

    override suspend fun setUserSessionId(userId: String) {
        userSessionStore.updateData { userSession ->
            userSession.toBuilder().setUserId(userId).build()
        }
    }

    override suspend fun deleteUserSession() {
        userSessionStore.updateData { userSession ->
            userSession.toBuilder().setUserId("").build()
        }
    }
}
