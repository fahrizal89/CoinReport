package com.fahrizal.coinreport.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fahrizal.coinreport.data.coin.dao.CoinDao
import com.fahrizal.coinreport.data.coin.model.Coin

@Database(
    version = 1,
    entities = [Coin::class],
    exportSchema = true
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao
}