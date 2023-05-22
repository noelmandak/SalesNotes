package com.example.salesnotes.data

import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("name") val customerName : String,
    @SerializedName("id")val transactionId : Int,
    @SerializedName("total")val transactionValue : Int,
    @SerializedName("status") val transactionStatus : String
)
