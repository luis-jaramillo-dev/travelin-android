package com.projectlab.core.presentation.ui.di

import com.projectlab.core.presentation.ui.utils.LocationUtils
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LocationUtilsEntryPoint {
    fun locationUtils(): LocationUtils
}