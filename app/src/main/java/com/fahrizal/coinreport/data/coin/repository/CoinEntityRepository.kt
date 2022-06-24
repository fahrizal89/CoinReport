package com.fahrizal.coinreport.data.coin.repository

import com.fahrizal.coinreport.data.coin.mapper.CoinPriceMapper.toCoins
import com.fahrizal.coinreport.data.coin.repository.source.local.LocalCoinEntityData
import com.fahrizal.coinreport.data.coin.repository.source.network.NetworkCoinEntityData
import com.fahrizal.coinreport.data.coin.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinEntityRepository @Inject constructor(
    private val localCoinEntityData: LocalCoinEntityData,
    private val networkCoinEntityData: NetworkCoinEntityData
) : CoinRepository {

    override fun getCoinPrices(refresh: Boolean): Flow<List<Coin>> {
        return networkCoinEntityData.getCoinPrices()
            .map { it.toCoins() }
    }
}