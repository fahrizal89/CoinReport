package com.fahrizal.coinreport.domain.usecase

import com.fahrizal.coinreport.data.coin.repository.CoinRepository
import com.fahrizal.coinreport.domain.model.CoinChart
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinPriceUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(): Flow<List<CoinChart>> {
        return coinRepository.getCoinCharts()
    }

}