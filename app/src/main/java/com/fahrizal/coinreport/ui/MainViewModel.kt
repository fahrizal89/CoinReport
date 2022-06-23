package com.fahrizal.coinreport.ui

import androidx.lifecycle.ViewModel
import com.fahrizal.coinreport.domain.usecase.GetCoinPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCoinPriceUseCase: GetCoinPriceUseCase
) : ViewModel() {

    init {
        getCoinPriceUseCase.invoke()
    }
}