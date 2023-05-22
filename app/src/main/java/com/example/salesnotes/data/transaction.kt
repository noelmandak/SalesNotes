package com.example.salesnotes.data

import java.util.Date

data class Transaction(
    val customerName : String,
    val transactionId : Int,
    val transactionValue : Int,
    val transactionStatus : String,
    val tanggal : Date
)
