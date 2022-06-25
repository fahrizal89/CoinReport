package com.fahrizal.coinreport.util

import java.util.*

object DateUtil {

    fun getBeginingDay(): Long =
        Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time.time
}