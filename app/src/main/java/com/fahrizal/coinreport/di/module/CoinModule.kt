package com.fahrizal.coinreport.di.module

import com.fahrizal.coinreport.data.coin.api.CoinApi
import com.fahrizal.coinreport.data.coin.repository.CoinEntityRepository
import com.fahrizal.coinreport.data.coin.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CoinModule {

    @Provides
    @Singleton
    fun provideCoinApi(retrofit: Retrofit): CoinApi {
        return retrofit.create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(coinEntityRepository: CoinEntityRepository): CoinRepository {
        return coinEntityRepository
    }
}