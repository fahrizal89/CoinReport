package com.fahrizal.coinreport.domain.usecase

import com.fahrizal.coinreport.data.coin.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class SyncCoinPriceUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(): Flow<Boolean> {
        return coinRepository.getCoinPrices(true)
            .flatMapConcat(coinRepository::saveCoinPrices)
    }
}