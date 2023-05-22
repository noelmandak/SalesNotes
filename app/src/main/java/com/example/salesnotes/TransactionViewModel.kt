package com.example.salesnotes

import android.content.ClipData.Item
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesnotes.RetrofitInstance.transactionService
import com.example.salesnotes.data.Items
import com.example.salesnotes.data.Stock
import com.example.salesnotes.data.Transaction
import kotlinx.coroutines.launch

class TransactionViewModel : ViewModel() {
//    lateinit var transactionArrayList: ArrayList<Transaction>

    private val _transactionLiveData: MutableLiveData<List<Transaction>> = MutableLiveData()
    val transactionLiveData : MutableLiveData<List<Transaction>> get() = _transactionLiveData

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


//    init {
//        var transactions = arrayListOf(
//            Transaction("Renata",1,5000,"Processed"),
//            Transaction("Jeni",2,10000,"Sent"),
//            Transaction("Timo",3,5000,"Canceled")
//        )
//        transactionArrayList = transactions
//    }
    }
}