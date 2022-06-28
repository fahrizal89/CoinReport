package com.fahrizal.coinreport.ui

import android.Manifest.permission.*
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.fahrizal.coin.common.ui.base.BaseActivity
import com.fahrizal.coinreport.data.coin.model.Coin
import com.fahrizal.coinreport.databinding.ActivityMainBinding
import com.fahrizal.coinreport.ui.adapter.TopCoinListAdapter
import com.fahrizal.coinreport.util.PermissionUtil.isAllowedToAccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var topCoinListAdapter: TopCoinListAdapter

    override fun constructViewBinding(): ViewBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun init(savedInstanceState: Bundle?) {
        checkLocationPermissions()
        observeUiState()
        viewModel.getCoinReport()
        initTopCoinData()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is MainViewModel.CoinUiState.Loaded -> updateCoinData(state.coins)
                        is MainViewModel.CoinUiState.Error -> showToast(state.stringRes)
                        else -> showLoading()
                    }
                }
            }
        }
    }

    private fun initTopCoinData() {
        getViewBinding().topCoinRv.run {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = topCoinListAdapter
        }
    }

    private fun updateCoinData(coins: List<Coin>) {
        topCoinListAdapter.update(coins)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        checkLocationPermissions()
    }

    private fun checkLocationPermissions() {
        val permissions = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)

        if (isAllowedToAccess(permissions, PERMISSION_MAP_CODE)) {
            isBackgroundLocationPermitted()
        }
    }

    private fun isBackgroundLocationPermitted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isAllowedToAccess(ACCESS_BACKGROUND_LOCATION, PERMISSION_BACKGROUND_MAP_CODE)
        } else {
            return true
        }
    }

    private fun showLoading() {}

    companion object {

        private const val PERMISSION_MAP_CODE = 1000
        private const val PERMISSION_BACKGROUND_MAP_CODE = 1003
    }
}