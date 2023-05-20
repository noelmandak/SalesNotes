package com.example.salesnotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesnotes.data.Stock

class StockViewModel : ViewModel() {
//    val searchKey = MutableLiveData<String>("")
    lateinit var stockArrayList: ArrayList<Stock>



    init {
        var stocks = arrayListOf(
            Stock("Nasi Goreng",0, 100,100,100),
            Stock("Ayam Goreng",1, 666,888,777)
        )
        stockArrayList = stocks
    }
}