package com.projectlab.core.domain.use_cases.users

import com.projectlab.core.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val repository: UsersRepository) {

    operator fun invoke(id: String) = repository.getUserById(id)

}