package com.example.salesnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesnotes.data.Item
import java.text.NumberFormat
import java.util.*

class CheckoutViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: MutableLiveData<List<Item>> get() = _items
    private val _totalPrice = MutableLiveData<String>()
    val totalPrice: LiveData<String> get() = _totalPrice
    val formatter = NumberFormat.getInstance(Locale.getDefault())


    init {
        _totalPrice.value = "0"
    }

    fun set_items(list_item:List<Item>){
        _items.value = list_item
        calculateTotalPrice()
    }

    fun calculateTotalPrice() {
        var total = 0
        for (item in items.value ?: emptyList()) {
            total += item.qty * item.price
        }
        val formattedTotalPrice = "Rp. ${formatter.format(total)}"
        _totalPrice.value = formattedTotalPrice
    }
    fun getTotalPrice(): String {
        var total = 0
        for (item in items.value!!) {
            total += item.qty * item.price
        }
        _totalPrice.value = total.toString()
        val priceStr = formatter.format(total).toString()
        return priceStr
    }
}