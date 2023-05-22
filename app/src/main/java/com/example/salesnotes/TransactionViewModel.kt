package com.example.salesnotes

import android.content.ClipData.Item
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesnotes.data.Items
import com.example.salesnotes.data.Transaction
import java.util.*
import kotlin.collections.ArrayList

class TransactionViewModel : ViewModel() {
    lateinit var transactionArrayList: ArrayList<Transaction>

    init {
        var transactions = arrayListOf(
            Transaction("Renata",1,5000,"Processed", Date(1970, 1, 27)),
            Transaction("Jeni",2,10000,"Sent",Date(1970, 1, 27)),
            Transaction("Timo",3,5000,"Canceled", Date(1970, 1, 27))
        )
        transactionArrayList = transactions
    }
}