package com.projectlab.travelin_android.di

import com.projectlab.auth.domain.repository.AuthRepository
import com.projectlab.auth.domain.use_cases.AuthUseCases
import com.projectlab.auth.domain.use_cases.GetCurrentUserUseCase
import com.projectlab.auth.domain.use_cases.LoginUseCase
import com.projectlab.auth.domain.use_cases.LogoutUseCase
import com.projectlab.auth.domain.use_cases.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AuthPresentationModule {

    @Provides
    fun provideAuthUseCase(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUserUseCase(repository),
        login = LoginUseCase(repository),
        logout = LogoutUseCase(repository),
        register = RegisterUseCase(repository)
    )

}