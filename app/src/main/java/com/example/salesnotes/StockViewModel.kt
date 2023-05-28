package com.example.salesnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesnotes.RetrofitInstance.stockService
import com.example.salesnotes.data.Stock
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {
    private val _stockLiveData: MutableLiveData<List<Stock>> = MutableLiveData()
    val stockLiveData : MutableLiveData<List<Stock>> get() = _stockLiveData
    private val toastMessage: MutableLiveData<String> = MutableLiveData()

    fun getAllStocks(){
        viewModelScope.launch {
            try {
                val stockList = stockService.getAllStocks()
                _stockLiveData.value = stockList
            } catch (e : Exception) {
                triggerToastMessage("Terjadi masalah jaringan")
            }
        }
    }
    fun getToastMessage(): LiveData<String> {
        return toastMessage
    }
    fun triggerToastMessage(message: String) {
        toastMessage.value = message
    }

}

