package com.example.salesnotes.data

data class Transaction(
    val customerName : String,
    val transactionId : Int,
    val transactionValue : Int,
    val transactionStatus : String
)
