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
    val chartName: String? = "",
    val updateTime: Long = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)