package com.fahrizal.coinreport.ui

import com.fahrizal.coinreport.data.coin.model.Coin
import com.fahrizal.coinreport.dispatcher.RealCoroutineDispatcherProvider
import com.fahrizal.coinreport.domain.usecase.GetCoinPriceUseCase
import com.fahrizal.coinreport.domain.usecase.SyncCoinPriceUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class MainViewModelTest {

    @Test
    fun getCoinReport_shouldReturn_coinList() = runBlocking {
        val realCoroutineDispatcherProvider = RealCoroutineDispatcherProvider()
        val getCoinPrices = Mockito.mock(GetCoinPriceUseCase::class.java)
        val syncCoinPriceUseCase = Mockito.mock(SyncCoinPriceUseCase::class.java)
        val mainViewModel =
            MainViewModel(realCoroutineDispatcherProvider, getCoinPrices, syncCoinPriceUseCase)
        val result = ArrayList<Coin>()
        result.add(Coin())
        Mockito.`when`(getCoinPrices()).thenReturn(flow { emit(result) })

        //when
        mainViewModel.getCoinReport()

        //then
        Mockito.verify(getCoinPrices).invoke()
        Assert.assertEquals(getCoinPrices.invoke().single(), result)
    }
}