package com.example.salesnotes.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.R
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemsAdapter(private  val productList: ArrayList<Items>) : RecyclerView.Adapter<ItemsAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ProductHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ProductHolder(itemView)

    }

    override fun onBindViewHolder(holder: ItemsAdapter.ProductHolder, position: Int) {
        val formatter = NumberFormat.getInstance(Locale.getDefault())

        val currentItem = productList[position]
        holder.namaBarang.text = currentItem.productName
        holder.hargaBarang.text =  "Rp ${formatter.format(currentItem.price)}"
        holder.stockBarang.text = "Stock : ${currentItem.stock}"
        holder.fotoBarang.setImageResource(currentItem.imgUrl)

    }

    override fun getItemCount(): Int {

        return productList.size
    }

    class  ProductHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val namaBarang : TextView = itemView.findViewById(R.id.namaBarangTextView)
        val stockBarang : TextView = itemView.findViewById(R.id.hargaBarangTextView)
        val hargaBarang : TextView = itemView.findViewById(R.id.hargaTotalBarangTextView)
        val fotoBarang : ImageView = itemView.findViewById(R.id.namaBarangImageView)

    }
}
