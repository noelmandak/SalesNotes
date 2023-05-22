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
//    lateinit var transactionArrayList: ArrayList<Transaction>
  
    private val _transactionLiveData: MutableLiveData<List<Transaction>> = MutableLiveData()
    val transactionLiveData : MutableLiveData<List<Transaction>> get() = _transactionLiveData
    var canceled:MutableLiveData<List<Int>> = MutableLiveData()

    init {
        var transactions = arrayListOf(
            Transaction("Renata",1,5000,"Processed", "1969-12-31 16:00:00"),
            Transaction("Jeni",2,10000,"Sent","1969-12-31 16:00:00"),
            Transaction("Timo",3,5000,"Canceled","1969-12-31 16:00:00")
        )
//        transactionArrayList = transactions
    }
    
    fun getAllTransactions(token: String){
        viewModelScope.launch {
            try {
                val transactionList = transactionService.getAllTransactions(token)
                _transactionLiveData.value = transactionList
            } catch (e : Exception) {
                // Tangani error
                e
            }
        }
    }        


    fun cancelTransaction(pos: Int) {
        transactionArrayList[pos].transactionStatus = "Canceled"
        transactionArrayList
    }
}



