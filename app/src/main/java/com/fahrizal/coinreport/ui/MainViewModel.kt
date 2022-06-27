package com.fahrizal.coinreport.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahrizal.coinreport.data.coin.model.Coin
import com.fahrizal.coinreport.dispatcher.CoroutineDispatcherProvider
import com.fahrizal.coinreport.domain.usecase.GetCoinPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val getCoinPriceUseCase: GetCoinPriceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CoinUiState>(CoinUiState.Empty)
    val uiState: StateFlow<CoinUiState> = _uiState

    fun getCoinReport() {
        viewModelScope.launch(coroutineDispatcherProvider.io) {
            getCoinPriceUseCase().catch {
                Timber.e("Fetch ERROR")
            }.collect {
                Timber.d("Fetch Success".plus(it.size))
            }
        }
    }

    sealed class CoinUiState {
        object Loading : CoinUiState()
        object Empty : CoinUiState()
        class Loaded(val coins: List<Coin>) : CoinUiState()
        class Error(@StringRes val stringRes: Int) : CoinUiState()
    }
}