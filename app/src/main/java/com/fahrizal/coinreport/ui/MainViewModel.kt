package com.fahrizal.coinreport.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahrizal.coinreport.dispatcher.CoroutineDispatcherProvider
import com.fahrizal.coinreport.domain.usecase.GetCoinPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val getCoinPriceUseCase: GetCoinPriceUseCase
) : ViewModel() {

    fun getReport(){
        viewModelScope.launch(coroutineDispatcherProvider.io) {
            getCoinPriceUseCase(true).catch {
                Timber.e("Fetch ERROR")
            }.collect {
                Timber.d("Fetch Success")
                it.size
            }
        }
    }
}