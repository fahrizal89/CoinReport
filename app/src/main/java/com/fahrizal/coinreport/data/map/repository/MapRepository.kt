package com.fahrizal.coinreport.data.map.repository

import com.fahrizal.coinreport.data.map.model.Location
import kotlinx.coroutines.flow.Flow

interface MapRepository {

    fun getLatitudeLongitude(): Flow<Location>
}