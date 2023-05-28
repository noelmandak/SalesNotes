package com.example.salesnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesnotes.RetrofitInstance.orderService
import com.example.salesnotes.data.CartItem
import com.example.salesnotes.data.CheckoutRequest
import com.example.salesnotes.data.Item
import com.example.salesnotes.data.OrderResponse
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class CheckoutViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: MutableLiveData<List<Item>> get() = _items
    private val _totalPrice = MutableLiveData<String>()
    val totalPrice: LiveData<String> get() = _totalPrice
    val formatter = NumberFormat.getInstance(Locale.getDefault())

    var orderRespons : MutableLiveData<OrderResponse> = MutableLiveData()

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

    fun createOrder(customerId: Int) {
        var cart = mutableListOf<CartItem>()
        for (item in items.value!!){
            cart.add(CartItem(item.id,item.qty))
        }
        viewModelScope.launch {
            try {
                val response = orderService.createOrder(CheckoutRequest(customerId, cart))
                if (response.isSuccessful) {
                    orderRespons.value = response.body()
                    // Lakukan tindakan yang sesuai dengan responsenya
                } else {
                    // Tangani jika responsenya tidak berhasil
                }
            } catch (e: Exception) {
                // Tangani error yang terjadi saat membuat permintaan
            }
        }
    }

}