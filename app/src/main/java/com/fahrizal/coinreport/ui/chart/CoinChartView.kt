package com.fahrizal.coinreport.ui.chart

import android.content.Context
import android.util.AttributeSet
import com.fahrizal.coinreport.data.coin.model.Coin
import com.fahrizal.coinreport.util.DateUtil
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class CoinChartView : LineChart {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
        : super(context, attrs, defStyleAttr)

    init {
        isDragEnabled = true
        xAxis.setDrawGridLines(false)
        axisRight.setDrawGridLines(false)
        // disable dual axis (only use LEFT axis)
        axisRight.isEnabled = false
        // horizontal grid lines
        axisLeft.enableGridDashedLine(10f, 10f, 0f)
    }

    fun updateData(coins: List<Coin>) {
        data = LineData(coins.groupingByCoinCode())
        updateRange(coins)
        notifyDataSetChanged()
    }

    private fun List<Coin>.groupingByCoinCode(): ArrayList<ILineDataSet> {
        val lineDataSet = ArrayList<ILineDataSet>()
        val map = HashMap<String, ArrayList<Entry>>()

        forEach { coin ->
            if (map[coin.code] == null) {
                val coins = ArrayList<Entry>()
                coins.add(Entry(0F, coin.rate?.toFloat() ?: 0F))
                map[coin.code ?: ""] = coins
            } else {
                val entries: ArrayList<Entry> = map[coin.code]!!
                val latestEntry: Entry = entries[entries.lastIndex]
                entries.add(Entry(latestEntry.x + 1, coin.rate?.toFloat() ?: 0F))
            }
        }

        for ((key, value) in map) {
            lineDataSet.add(LineDataSet(value, key))
        }

        return lineDataSet
    }

    private fun updateRange(coins: List<Coin>) {
        setVisibleXRangeMaximum(5f)
        moveViewToX(coins.size.toFloat())
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return if (value < 1) ""
                else {
                    DateUtil.getTimeString(coins[value.toInt()].updateTime)
                }
            }
        }
    }
}