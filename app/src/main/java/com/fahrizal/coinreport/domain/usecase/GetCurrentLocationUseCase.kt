package com.fahrizal.coinreport.domain.usecase

import com.fahrizal.coinreport.data.map.model.Location
import com.fahrizal.coinreport.data.map.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val mapRepository: MapRepository
) {

    operator fun invoke(): Flow<Location> {
        return mapRepository.getLatitudeLongitude()
    }
}