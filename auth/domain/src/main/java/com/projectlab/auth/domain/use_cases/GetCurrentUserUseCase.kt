package com.projectlab.auth.domain.use_cases

import com.projectlab.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.currentUser
}
