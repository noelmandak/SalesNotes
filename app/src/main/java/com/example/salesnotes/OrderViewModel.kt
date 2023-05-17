package com.example.salesnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesnotes.data.Items
import com.example.salesnotes.data.ItemsAdapter

class OrderViewModel : ViewModel() {
    val category = arrayListOf<String>("Food","Drink","Snacks")
    var cart = mutableListOf<Int>()
    lateinit var productArrayList : ArrayList<Items>
    var searchKey = MutableLiveData<String>("")
    val filteredProductList = MutableLiveData<List<Items>>()

    init {
        var products =  arrayListOf(
            Items("Nasi Goreng", 0,15000,10, false,R.drawable.nasigoreng),
            Items("Ayam Goreng", 1,17000,10, true,R.drawable.ayamgoreng),
            Items("Pisang Goreng", 2,5000,10, false,R.drawable.pisanggoreng),
            Items("Tahu Goreng", 3,5000,10, false, R.drawable.tahugoreng),
            Items("Jamur Goreng", 4,5000,10, false, R.drawable.jamurgoreng),
            Items("Tempe Goreng", 5,5000,10, false, R.drawable.tempegoreng),
            Items("Mie Goreng", 6,15000,10, false, R.drawable.miegoreng),
        )
        productArrayList = products
        getProductData()
    }

    fun getProductData() {
        // ...

        this.onTextChanged()
//        filteredProductList.value = productArrayList
    }

    fun onCheckboxClicked(id:Int, isChecked:Boolean) {
        for (item in productArrayList){
            if (item.id == id) {
                item.isChecked=isChecked
                if (isChecked) { this.cart.add(item.id) }
                else { this.cart.remove(item.id) }

                this.searchKey.value
            }
        }
    }
    fun onTextChanged() {
        val text = this.searchKey.value.toString()
        val filteredList = if (text.isBlank()) {
            productArrayList // Menampilkan semua item jika searchKey kosong
        } else {
            productArrayList.filter { it.productName.contains(text, ignoreCase = true) }
        }
        text
        filteredList
        filteredProductList.value = filteredList
    }
    fun onDeleteFirst() {
        val currentList = filteredProductList.value?.toMutableList()
        if (!currentList.isNullOrEmpty()){
            currentList.removeAt(0)
        }
        currentList
        if (currentList != null) {
            filteredProductList.value = currentList.toMutableList()
        }
    }
}