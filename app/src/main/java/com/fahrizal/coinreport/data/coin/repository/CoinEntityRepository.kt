package com.fahrizal.coinreport.data.coin.repository

import com.fahrizal.coinreport.data.coin.model.Coin
import com.fahrizal.coinreport.data.coin.model.CoinPriceResponse
import com.fahrizal.coinreport.data.coin.repository.source.local.LocalCoinEntityData
import com.fahrizal.coinreport.data.coin.repository.source.network.NetworkCoinEntityData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinEntityRepository @Inject constructor(
    private val localCoinEntityData: LocalCoinEntityData,
    private val networkCoinEntityData: NetworkCoinEntityData,
) : CoinRepository {

    override fun fetchCoinPrices(refresh: Boolean): Flow<CoinPriceResponse> {
        return networkCoinEntityData.getCoinPrices()
    }

    override fun getSavedCoinPrices(): Flow<List<Coin>> {
        return localCoinEntityData.getCoinPrices()
    }

    override fun saveCoinPrices(coins: List<Coin>): Flow<Boolean> {
        return localCoinEntityData.saveCoinPrices(coins)
    }

    override fun deleteCoinPricesLowerThenTime(time: Long): Flow<Boolean> {
        return localCoinEntityData.deleteCoinPricesLowerThenTime(time)
    }
}