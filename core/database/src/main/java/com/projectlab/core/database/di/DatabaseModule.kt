package com.projectlab.core.database.di

import com.projectlab.core.domain.repository.UsersRepository
import com.projectlab.core.domain.use_cases.users.CreateUserUseCase
import com.projectlab.core.domain.use_cases.users.GetUserByIdUseCase
import com.projectlab.core.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        createUser = CreateUserUseCase(repository),
        getUserById = GetUserByIdUseCase(repository),
    )

}