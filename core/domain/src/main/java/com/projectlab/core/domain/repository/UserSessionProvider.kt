package com.projectlab.core.domain.repository

/**
 * Interface for providing the current user session.
 *
 * - getUserSessionId() returns the user ID for the current session.
 * - setUserSessionId() sets the user ID for the current session.
 * - deleteUserSession() deletes the user session data.
 */
interface UserSessionProvider {
    suspend fun getUserSessionId(): String?
    suspend fun setUserSessionId(userId: String)
    suspend fun deleteUserSession()
}
