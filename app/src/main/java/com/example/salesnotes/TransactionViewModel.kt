package com.example.salesnotes

import android.content.ClipData.Item
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesnotes.RetrofitInstance.transactionService
import com.example.salesnotes.data.Items
import com.example.salesnotes.data.Stock
import com.example.salesnotes.data.Transaction
import java.util.*
import kotlin.collections.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.launch
class TransactionViewModel : ViewModel() {
  
    private val _transactionLiveData: MutableLiveData<List<Transaction>> = MutableLiveData()
    val transactionLiveData : MutableLiveData<List<Transaction>> get() = _transactionLiveData
    val refreshTrigger: MutableLiveData<Unit> = MutableLiveData()

    fun triggerRefresh() {
        refreshTrigger.value = Unit
    }
    fun cancelOrder(transactionId: String){
        viewModelScope.launch {
            try {
                val status = transactionService.cancelOrder(transactionId).status
                if (status == "success") {
                    triggerRefresh()
                }
            } catch (e: Exception) {

            }
        }
    }

    fun getAllTransactions(token: String){
        viewModelScope.launch {
            try {
                val transactionList = transactionService.getAllTransactions(token)
                if (_transactionLiveData.value!=transactionList){
                    _transactionLiveData.value = transactionList
                }
            } catch (e : Exception) {
                // Tangani error
                e
            }
        }
    }
}



