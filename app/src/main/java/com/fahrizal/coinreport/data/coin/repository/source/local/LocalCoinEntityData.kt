package com.fahrizal.coinreport.data.coin.repository.source.local

import com.fahrizal.coinreport.data.coin.dao.CoinDao
import com.fahrizal.coinreport.data.coin.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalCoinEntityData @Inject constructor(
    private val coinDao: CoinDao
) {

    fun getCoinPrices(): Flow<List<Coin>> {
        return coinDao.getCoins()
    }

    fun saveCoinPrices(coins: List<Coin>): Flow<Boolean> {
        return flow {
            coinDao.insert(coins)
            emit(true)
        }
    }

    fun deleteCoinPricesLowerThenTime(time: Long): Flow<Boolean> {
        return flow {
            coinDao.delete(time)
            emit(true)
        }
    }
}