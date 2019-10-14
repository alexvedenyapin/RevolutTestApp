package com.alexvedenyapin.revoluttestapp.screen.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexvedenyapin.revoluttestapp.R
import kotlinx.android.synthetic.main.item_currency_rate.view.*
import java.text.DecimalFormat

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
class CurrenciesRatesAdapter(
    private val onCurrencyRateClick: (String) -> Unit,
    private val onCurrencyValueChanged: (Double) -> Unit
) : ListAdapter<CurrencyRateListItem, RecyclerView.ViewHolder>(RatesListDiffCallback()) {

    private var selectedCurrency: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)
        return CurrencyRateHolder(view, onCurrencyRateClick, onCurrencyValueChanged)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindCurrencyRateInfo(holder as CurrencyRateHolder, getItem(position))
    }

    override fun getItemViewType(position: Int) = R.layout.item_currency_rate

    fun setCurrency(selectedCurrency: String) {
        this.selectedCurrency = selectedCurrency
    }

    private fun bindCurrencyRateInfo(holder: CurrencyRateHolder, item: CurrencyRateListItem) =
        with(holder.itemView) {
            holder.item = item
            currencyName.text = item.currency
            currencyValue.setText(if (item.value > 0) formatValue(item) else "")
        }

    private fun formatValue(item: CurrencyRateListItem): String? {
        val df = DecimalFormat("##.##")
        val formattedValue = df.format(item.value)
        return if (formattedValue.contains(",")) formattedValue.replace(',', '.') else formattedValue
    }

    inner class CurrencyRateHolder(
        itemView: View,
        onCurrencyRateClick: (String) -> Unit,
        onCurrencyValueChanged: (Double) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        var item: CurrencyRateListItem? = null

        init {
            itemView.setOnClickListener {
                itemView.currencyValue.requestFocus()
                item?.let {
                    onCurrencyRateClick(it.currency)
                }
            }
            itemView.currencyValue.addTextChangedListener(TextWatcherAdapter { value ->
                val updatedValue: Double = if (value.isEmpty()) 0.0 else value.toDouble()
                item?.let {
                    if (it.currency == selectedCurrency) {
                        onCurrencyValueChanged(updatedValue)
                    }
                }
            })
        }
    }
}

private class RatesListDiffCallback : DiffUtil.ItemCallback<CurrencyRateListItem>() {

    override fun areItemsTheSame(
        oldItem: CurrencyRateListItem,
        newItem: CurrencyRateListItem
    ): Boolean {
        return oldItem.currency == newItem.currency
    }

    override fun areContentsTheSame(
        oldItem: CurrencyRateListItem,
        newItem: CurrencyRateListItem
    ): Boolean {
        return oldItem == newItem
    }
}