package com.example.salesnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesnotes.RetrofitInstance.itemService
import com.example.salesnotes.data.Item
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {
    val category = arrayListOf<String>("All","Foods","Beverages","Snacks")
    var cart = mutableListOf<Int>()
    var searchKey = MutableLiveData<String>("")
    private val toastMessage: MutableLiveData<String> = MutableLiveData()
    private val _items = MutableLiveData<List<Item>>()
    val filteredItemList: MutableLiveData<List<Item>> = MutableLiveData()
    val items: MutableLiveData<List<Item>> get() {return _items }

    init {
        getAllItems()
    }

    fun getToastMessage(): LiveData<String> {
        return toastMessage
    }
    fun triggerToastMessage(message: String) {
        toastMessage.value = message
    }
    fun filterItemsByCategory(category: String) {
        val items = _items.value
        items?.let {
            val filteredItems = if (category == "all") {
                it // Tampilkan semua item jika kategori "Semua Kategori" dipilih
            } else {
                it.filter { item -> item.category == category }
            }
            filteredItemList.value = filteredItems
        }
    }
    fun searchItemsByName(searchText: String) {
        val items = _items.value
        items?.let {
            val filteredItems = it.filter { item ->
                item.productName.contains(searchText, ignoreCase = true)
            }
            filteredItemList.value = filteredItems
        }
    }
    fun getAllItems() {
        viewModelScope.launch {
            try {
                val itemList = itemService.getAllItems()
                for (i in itemList.indices) {
                    itemList[i].isChecked = itemList[i].id in cart
                    itemList[i].qty = 0
                }
                _items.value = itemList
                filteredItemList.value = itemList
            } catch (e: Exception) {
                triggerToastMessage("Terjadi masalah jaringan")
            }
        }
    }
    fun goToCheckout(): MutableList<Item> {
        var checkoutItem = mutableListOf<Item>()
        for (item in _items.value!!) {
            if (item.id in cart){
                item.qty = 1
                checkoutItem.add(item)
            }
        }
        return checkoutItem
    }
    fun selectItem(id:Int, isChecked:Boolean) {
        for (item in items.value!!){
            if (item.id == id) {
                item.isChecked=isChecked
                if (isChecked) { this.cart.add(item.id) }
                else { this.cart.remove(item.id) }
                this.cart
            }
        }
    }
}