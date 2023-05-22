package com.example.salesnotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesnotes.RetrofitInstance.stockService
import com.example.salesnotes.data.Stock
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {
//    val apiService = RetrofitInstance.apiService
//    val searchKey = MutableLiveData<String>("")
//    lateinit var stockArrayList: ArrayList<Stock>
    private val _stockLiveData: MutableLiveData<List<Stock>> = MutableLiveData()
    val stockLiveData : MutableLiveData<List<Stock>> get() = _stockLiveData
    val category = arrayListOf<String>("All","Food","Drink","Snacks")

    //    init {
//        getAllStocks()
//    }
    fun getAllStocks(){
        viewModelScope.launch {
            try {
                val stockList = stockService.getAllStocks()
                stockList
                _stockLiveData.value = stockList
            } catch (e : Exception) {
                // Tangani error
                e
            }
        }


//        val StockApiService = RetrofitInstance.stockService
//        val call = StockApiService.getAllStocks()
//
//        call.enqueue(object : Callback<List<Stock>> {
//            override fun onResponse(call: Call<List<Stock>>, response: Response<List<Stock>>) {
//                response
//                if (response.isSuccessful) {
//                    val stocks = response.body()
//                    stocks
//                    stockLiveData.value = stocks
//                } else {
//                    // Menghandle kesalahan respons
//                }
//            }
//
//            override fun onFailure(call: Call<List<Stock>>, t: Throwable) {
//                // Menghandle kegagalan permintaan
//            }
//        })
    }


//    init {
//        var stocks = arrayListOf(
//            Stock("Nasi Goreng",0, 100,100,100),
//            Stock("Ayam Goreng",1, 666,888,777)
//        )
//        stockArrayList = stocks
//    }
}

