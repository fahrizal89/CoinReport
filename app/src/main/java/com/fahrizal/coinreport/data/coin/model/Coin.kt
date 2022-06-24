package com.fahrizal.coinreport.data.coin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin")
data class Coin(
    @PrimaryKey val id: Int? = null,
    val code: String? = "",
    val symbol: String? = "",
    val rate: Double? = 0.0,
    val description: String? = "",
    val rateFloat: Double? = 0.0,
    val chartName: String? = "",
    val updateTime: Long = 0
)