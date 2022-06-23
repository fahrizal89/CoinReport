package com.fahrizal.coinreport.domain.usecase

import com.fahrizal.coinreport.data.coin.repository.CoinRepository
import javax.inject.Inject

class GetCoinPriceUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke() {
        coinRepository.getCoinPrices()
    }

}