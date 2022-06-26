package com.fahrizal.coinreport.data.coin.dao

import androidx.room.*
import com.fahrizal.coinreport.data.coin.model.Coin
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coins: List<Coin>)

    @Query("SELECT * FROM coin")
    fun getCoins(): Flow<List<Coin>>

    @Query("DELETE FROM coin WHERE coin.updateTime < :time")
    fun delete(time: Long)
}