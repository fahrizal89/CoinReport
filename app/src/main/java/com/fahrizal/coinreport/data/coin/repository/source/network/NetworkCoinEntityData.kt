package com.fahrizal.coinreport.data.coin.repository.source.network

import com.fahrizal.coinreport.data.coin.api.CoinApi
import com.fahrizal.coinreport.data.coin.model.CoinPriceResponse
import com.fahrizal.coinreport.data.coin.repository.source.CoinEntityData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkCoinEntityData @Inject constructor(
    private val coinApi: CoinApi
) : CoinEntityData {

    override fun getCoinPrices(): Flow<CoinPriceResponse> {
        return flow {
            emit(coinApi.getCurrentPrice())
        }
    }
}