package com.fahrizal.coinreport.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.fahrizal.coinreport.data.coin.model.Coin
import com.fahrizal.coinreport.databinding.LayoutTopCoinBinding
import com.fahrizal.coinreport.util.DateUtil
import javax.inject.Inject

class TopCoinListAdapter @Inject constructor() :
    RecyclerView.Adapter<TopCoinListAdapter.TopCoinViewHolder>() {

    private val coins = ArrayList<Coin>()

    class TopCoinViewHolder(binding: LayoutTopCoinBinding) : RecyclerView.ViewHolder(binding.root) {

        val currency = binding.currency
        val rate = binding.rateTv
        val date = binding.dateTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCoinViewHolder {
        return TopCoinViewHolder(LayoutTopCoinBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TopCoinViewHolder, position: Int) {
        holder.apply {
            val symbol =
                HtmlCompat.fromHtml(coins[position].symbol ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
            rate.text = symbol.toString().plus(" ").plus(coins[position].rate.toString())
            date.text = DateUtil.getDateString(coins[position].updateTime)
            currency.text = coins[position].description.toString()
        }
    }

    override fun getItemCount(): Int = coins.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newCoins: List<Coin>) {
        coins.run {
            clear()
            addAll(newCoins.getTopDataOnly())
            notifyDataSetChanged()
        }
    }

    private fun List<Coin>.getTopDataOnly(): List<Coin> {
        if (this.size > MAX_DATA) {
            val newCoins = ArrayList<Coin>()
            for (i: Int in 0 until  MAX_DATA) {
                newCoins.add(this[size-1-i])
            }
            return newCoins
        }
        return this
    }

    companion object {

        private const val MAX_DATA = 5
    }
}