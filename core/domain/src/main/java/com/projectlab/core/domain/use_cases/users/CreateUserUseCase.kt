package com.projectlab.core.domain.use_cases.users

import com.projectlab.core.domain.model.User
import com.projectlab.core.domain.repository.UsersRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(user: User) = repository.createUser(user)
}