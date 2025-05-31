package com.projectlab.core.data.di

import com.projectlab.core.data.service.GeocoderService
import com.projectlab.core.data.service.GeocoderServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GeocoderModule {

    @Binds
    abstract fun bindGeocoderService(
        impl: GeocoderServiceImpl
    ): GeocoderService
}