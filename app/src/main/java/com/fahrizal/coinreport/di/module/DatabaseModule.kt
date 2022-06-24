package com.fahrizal.coinreport.di.module

import com.fahrizal.coinreport.data.db.AppDatabase
import com.fahrizal.coinreport.data.db.AppRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideMyRoomDatabase(appDatabase: AppDatabase): AppRoomDatabase {
        return appDatabase.roomDb
    }
}