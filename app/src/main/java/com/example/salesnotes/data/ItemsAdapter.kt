package com.example.salesnotes.data

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
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
//            holder.namaBarang.text = currentItem.productName
            holder.hargaBarang.text =  "Rp ${formatter.format(currentItem.price)}"
            holder.stockBarang.text = "Stock : ${currentItem.stock}"
            holder.checkbox.setChecked(currentItem.isChecked)
            if (currentItem.stock == 0) {
                holder.checkbox.isEnabled = false
            }
            holder.checkbox.setOnCheckedChangeListener {_, isChecked->
                viewModel.selectItem(currentItem.id,isChecked)
            }
            var imageUrl = RetrofitInstance.BASE_URL + currentItem.imgUrl

            Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.food_loading) // Gambar placeholder yang ditampilkan sementara
                .error(R.drawable.img_error) // Gambar error yang ditampilkan jika terjadi kesalahan
                .centerCrop() // Atur gambar menjadi berukuran yang sesuai dengan ImageView dan memotong jika perlu
                .into(holder.fotoBarang)

            if (currentItem.stock == 0) {
                // Mencoret nama item
                val spannableString = SpannableString(currentItem.productName)
                spannableString.setSpan(StrikethroughSpan(), 0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                holder.namaBarang.text = spannableString
            } else {
                // Nama item normal
                holder.namaBarang.text = currentItem.productName
            }
        }


    }

    override fun getItemCount(): Int {

        return filteredProductList.value?.size?:0
    }

    class  ProductHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val namaBarang : TextView = itemView.findViewById(R.id.namaBarangTextView)
        val stockBarang : TextView = itemView.findViewById(R.id.hargaBarangTextView)
        val hargaBarang : TextView = itemView.findViewById(R.id.hargaBarangCheckoutTextView)
        val fotoBarang : ImageView = itemView.findViewById(R.id.namaBarangImageView)
        val checkbox : CheckBox = itemView.findViewById(R.id.productcheckBox)
    }
}
