package com.fahrizal.coinreport.di.module

import com.fahrizal.coinreport.data.map.repository.MapEntityRepository
import com.fahrizal.coinreport.data.map.repository.MapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MapModule {

    @Provides
    @Singleton
    fun provideMapRepository(mapEntityRepository: MapEntityRepository): MapRepository {
        return mapEntityRepository
    }
}