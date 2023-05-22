package com.example.salesnotes.data

import com.google.gson.annotations.SerializedName

data class Stock(
    @SerializedName("item_name") val item_name : String,
    @SerializedName("item_id") val item_id : Int,
    @SerializedName("warehouse_stock")val warehouse_stock : Int,
    @SerializedName("order_qty") val order_qty : Int,
    @SerializedName("available_qty") val available_qty : Int
)
