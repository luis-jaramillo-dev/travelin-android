package com.projectlab.core.domain.use_cases.users

data class UsersUseCases(
    val createUser: CreateUserUseCase,
    val getUserById: GetUserByIdUseCase
)