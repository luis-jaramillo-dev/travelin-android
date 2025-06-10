package com.projectlab.auth.domain.use_cases

data class AuthUseCases(
    val getCurrentUser: GetCurrentUserUseCase,
    val login: LoginUseCase,
    val logout: LogoutUseCase,
    val register: RegisterUseCase,
)
