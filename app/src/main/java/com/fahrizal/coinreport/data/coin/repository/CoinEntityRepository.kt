package com.fahrizal.coinreport.data.coin.repository

import com.fahrizal.coinreport.data.coin.mapper.CoinPriceMapper.toCoinCharts
import com.fahrizal.coinreport.data.coin.repository.source.local.LocalCoinEntityData
import com.fahrizal.coinreport.data.coin.repository.source.network.NetworkCoinEntityData
import com.fahrizal.coinreport.domain.model.CoinChart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinEntityRepository @Inject constructor(
    private val localCoinEntityData: LocalCoinEntityData,
    private val networkCoinEntityData: NetworkCoinEntityData
) : CoinRepository {

    override fun getCoinCharts(): Flow<List<CoinChart>> {
        return networkCoinEntityData.getCoinPrices()
            .map { it.toCoinCharts() }
    }
}