package com.example.salesnotes

import android.content.ClipData.Item
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesnotes.data.Items
import com.example.salesnotes.data.Transaction

class TransactionViewModel : ViewModel() {
    lateinit var transactionArrayList: ArrayList<Transaction>

    init {
        var transactions = arrayListOf(
            Transaction("Renata",1,5000,"Processed"),
            Transaction("Jeni",2,10000,"Sent"),
            Transaction("Timo",3,5000,"Canceled")
        )
        transactionArrayList = transactions
    }
}