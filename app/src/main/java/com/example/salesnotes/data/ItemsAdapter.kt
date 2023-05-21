package com.example.salesnotes.data

import android.content.Context
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
import com.bumptech.glide.Glide
import com.example.salesnotes.RetrofitInstance
import java.util.*

class ItemsAdapter(private val filteredProductList: MutableLiveData<List<Item>>, private val viewModel: OrderViewModel, private val context: Context) : RecyclerView.Adapter<ItemsAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ProductHolder(itemView)

    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val formatter = NumberFormat.getInstance(Locale.getDefault())

        val currentItem = filteredProductList.value?.get(position)
        if (currentItem != null) {
            currentItem
            holder.namaBarang.text = currentItem.productName
            holder.hargaBarang.text =  "Rp ${formatter.format(currentItem.price)}"
            holder.stockBarang.text = "Stock : ${currentItem.stock}"
            holder.checkbox.setChecked(currentItem.isChecked)
            holder.checkbox.setOnCheckedChangeListener {_, isChecked->
                viewModel.onCheckboxClicked(currentItem.id,isChecked)

            }
            var imageUrl = RetrofitInstance.BASE_URL + currentItem.imgUrl

            Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.nasigoreng) // Gambar placeholder yang ditampilkan sementara
                .error(R.drawable.miegoreng) // Gambar error yang ditampilkan jika terjadi kesalahan
                .centerCrop() // Atur gambar menjadi berukuran yang sesuai dengan ImageView dan memotong jika perlu
                .into(holder.fotoBarang)
        }


    }

//    fun setData(newProductList: List<Items>) {
//        filteredProductList.value = newProductList
//        notifyDataSetChanged()
//    }
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
