package com.fahrizal.coin.common.util

import android.graphics.Color

object ColorUtil {

    fun getRandomColor(): Int {
        return Color.rgb((30..200).random(), (30..200).random(), (30..200).random())
    }
}