package com.fahrizal.coinreport.data.coin.api

import com.fahrizal.coinreport.data.coin.model.CoinPriceResponse
import retrofit2.http.GET

interface CoinApi {

    @GET("v1/bpi/currentprice.json")
    suspend fun getCurrentPrice(): CoinPriceResponse
}