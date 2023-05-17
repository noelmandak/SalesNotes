package com.example.salesnotes.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.OrderViewModel
import com.example.salesnotes.R
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemsAdapter(private  val filteredProductList: MutableLiveData<List<Items>>, private  val viewModel : OrderViewModel) : RecyclerView.Adapter<ItemsAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ProductHolder(itemView)

    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val formatter = NumberFormat.getInstance(Locale.getDefault())

        val currentItem = filteredProductList.value?.get(position)
        if (currentItem != null) {
            holder.namaBarang.text = currentItem.productName
            holder.hargaBarang.text =  "Rp ${formatter.format(currentItem.price)}"
            holder.stockBarang.text = "Stock : ${currentItem.stock}"
            holder.fotoBarang.setImageResource(currentItem.imgUrl)
            holder.checkbox.setChecked(currentItem.isChecked)
            holder.checkbox.setOnCheckedChangeListener {_, isChecked->
                viewModel.onCheckboxClicked(currentItem.id,isChecked)
            }
        }


    }

    fun setData(newProductList: List<Items>) {
        filteredProductList.value = newProductList
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {

        return filteredProductList.value?.size?:0
    }

    class  ProductHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val namaBarang : TextView = itemView.findViewById(R.id.namaBarangTextView)
        val stockBarang : TextView = itemView.findViewById(R.id.hargaBarangTextView)
        val hargaBarang : TextView = itemView.findViewById(R.id.hargaTotalBarangTextView)
        val fotoBarang : ImageView = itemView.findViewById(R.id.namaBarangImageView)
        val checkbox : CheckBox = itemView.findViewById(R.id.productcheckBox)
    }
}
