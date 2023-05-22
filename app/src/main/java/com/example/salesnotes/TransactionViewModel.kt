package com.example.salesnotes

import android.content.ClipData.Item
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesnotes.data.Items
import com.example.salesnotes.data.Transaction
import java.util.*
import kotlin.collections.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TransactionViewModel : ViewModel() {
    lateinit var transactionArrayList: ArrayList<Transaction>
    var canceled:MutableLiveData<List<Int>> = MutableLiveData()

    init {
        var transactions = arrayListOf(
            Transaction("Renata", 1, 5000, "Processed", Date(1970, 1, 27)),
            Transaction("Jeni", 2, 10000, "Sent", Date(1970, 1, 27)),
            Transaction("Timo", 3, 5000, "Canceled", Date(1970, 1, 27))
        )
        transactionArrayList = transactions
    }


    fun cancelTransaction(pos: Int) {
        transactionArrayList[pos].transactionStatus = "Canceled"
        transactionArrayList
    }
}



