package com.projectlab.core.domain.use_cases.users

import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.repository.UsersRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(userEntity: UserEntity) = repository.create(userEntity)
}