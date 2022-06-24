package com.fahrizal.coinreport.domain.usecase

import com.fahrizal.coinreport.data.coin.repository.CoinRepository
import com.fahrizal.coinreport.data.coin.model.Coin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinPriceUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(refresh:Boolean): Flow<List<Coin>> {
        return coinRepository.getCoinPrices(refresh)
    }

}