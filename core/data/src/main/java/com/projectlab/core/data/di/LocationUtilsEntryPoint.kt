package com.projectlab.core.data.di


import com.projectlab.core.data.utils.LocationUtils
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LocationUtilsEntryPoint {
    fun locationUtils(): LocationUtils
}