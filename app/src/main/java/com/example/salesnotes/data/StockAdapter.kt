package com.example.salesnotes.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.R

class StockAdapter(private val stockList: MutableLiveData<List<Stock>>) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.list_stock, parent, false)
        return StockViewHolder(itemview)
    }

    override fun getItemCount(): Int {
        return stockList.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentStock = stockList.value?.get(position)
        if (currentStock != null) {
            holder.itemName.text = currentStock.item_name
            holder.itemId.text = "ID: ${currentStock.item_id}"
            holder.stockText.text = currentStock.warehouse_stock.toString()
            holder.qtyText.text = currentStock.order_qty.toString()
            holder.availableText.text = currentStock.available_qty.toString()
            }
        }

    class StockViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){

        val itemName : TextView = itemView.findViewById(R.id.itemName)
        val itemId : TextView = itemView.findViewById(R.id.itemId)
        val stockText : TextView = itemView.findViewById(R.id.stockText)
        val qtyText : TextView = itemView.findViewById(R.id.qtyText)
        val availableText : TextView = itemView.findViewById((R.id.availableText))
    }
}