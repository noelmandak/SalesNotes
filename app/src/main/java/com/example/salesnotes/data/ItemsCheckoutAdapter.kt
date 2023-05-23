package com.example.salesnotes.data

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.salesnotes.CheckoutViewModel
import com.example.salesnotes.R
import com.example.salesnotes.RetrofitInstance
import java.text.NumberFormat
import java.util.*

class ItemsCheckoutAdapter(private  val productList: MutableLiveData<List<Item>>, private val viewModel: CheckoutViewModel, private val context: Context) : RecyclerView.Adapter<ItemsCheckoutAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsCheckoutAdapter.ProductHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_checkout, parent, false)
        return ProductHolder(itemView)

    }

    override fun onBindViewHolder(holder: ItemsCheckoutAdapter.ProductHolder, position: Int) {
        val formatter = NumberFormat.getInstance(Locale.getDefault())


        val currentItem = productList.value?.get(position)
        currentItem
        if (currentItem != null) {
            holder.namaBarang.text = currentItem.productName
            holder.hargaBarang.text =  "Rp ${formatter.format(currentItem.price)}"
            holder.stockBarang.text = "Stock : ${currentItem.stock}"
            holder.qtyBarang.setText(currentItem.qty.toString())
            holder.hargaTotalBarang.text = "Total: Rp ${formatter.format(currentItem.price*currentItem.qty)}"

            var imageUrl = RetrofitInstance.BASE_URL + currentItem.imgUrl
            Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.nasigoreng) // Gambar placeholder yang ditampilkan sementara
                .error(R.drawable.miegoreng) // Gambar error yang ditampilkan jika terjadi kesalahan
                .centerCrop() // Atur gambar menjadi berukuran yang sesuai dengan ImageView dan memotong jika perlu
                .into(holder.fotoBarang)

            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Aksi yang akan dijalankan ketika teks berubah
                    val newText = s.toString().toIntOrNull()

                    if (newText==null) {
                        currentItem.qty = 0
                    } else {
                        if (newText>currentItem.stock){
                            currentItem.qty = currentItem.stock
                            holder.qtyBarang.setText(currentItem.qty.toString())
                            holder.hargaBarang.text = "Rp ${formatter.format(currentItem.price)}"
                            holder.hargaTotalBarang.text = "Total: Rp ${formatter.format(currentItem.price*currentItem.qty)}"
                        } else {
                            currentItem.qty = newText
                            holder.hargaTotalBarang.text = "Total: Rp ${formatter.format(currentItem.price*currentItem.qty)}"
                        }
                    }
                    viewModel.calculateTotalPrice()
                }
            }
            holder.qtyBarang.addTextChangedListener(textWatcher)
        }
    }

    override fun getItemCount(): Int {

        return productList.value?.size ?: 0
    }

    class  ProductHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val namaBarang : TextView = itemView.findViewById(R.id.namaBarangTextView)
        val stockBarang : TextView = itemView.findViewById(R.id.hargaBarangTextView)
        val hargaBarang : TextView = itemView.findViewById(R.id.hargaBarangCheckoutTextView)
        val hargaTotalBarang : TextView = itemView.findViewById(R.id.hargaTotalBarangTextView2)
        val fotoBarang : ImageView = itemView.findViewById(R.id.namaBarangImageView)
        val qtyBarang : EditText = itemView.findViewById(R.id.qtyEditText)



    }
}
