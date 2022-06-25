package com.fahrizal.coinreport.data.map.repository

import com.fahrizal.coinreport.data.map.model.Location
import com.fahrizal.coinreport.data.map.repository.source.local.LocalMapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MapEntityRepository @Inject constructor(
    private val localMapRepository: LocalMapRepository
) : MapRepository {

    override fun getLatitudeLongitude(): Flow<Location> {
        return localMapRepository.getLatitudeLongitude()
    }
}