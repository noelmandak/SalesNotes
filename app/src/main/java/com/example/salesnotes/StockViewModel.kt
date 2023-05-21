package com.example.salesnotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesnotes.data.Stock

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StockViewModel : ViewModel() {
//    val apiService = RetrofitInstance.apiService
//    val searchKey = MutableLiveData<String>("")
//    lateinit var stockArrayList: ArrayList<Stock>
    val stockLiveData: MutableLiveData<List<Stock>?> = MutableLiveData()

    fun getAllStocks(token: String){
        val StockApiService = RetrofitInstance.stockService
        val call = StockApiService.getAllStocks(token)

        call.enqueue(object : Callback<List<Stock>> {
            override fun onResponse(call: Call<List<Stock>>, response: Response<List<Stock>>) {
                response
                if (response.isSuccessful) {
                    val stocks = response.body()
                    stocks
                    stockLiveData.value = stocks
                } else {
                    // Menghandle kesalahan respons
                }
            }

            override fun onFailure(call: Call<List<Stock>>, t: Throwable) {
                // Menghandle kegagalan permintaan
            }
        })
    }


//    init {
//        var stocks = arrayListOf(
//            Stock("Nasi Goreng",0, 100,100,100),
//            Stock("Ayam Goreng",1, 666,888,777)
//        )
//        stockArrayList = stocks
//    }
}

