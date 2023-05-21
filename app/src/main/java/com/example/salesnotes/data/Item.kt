package com.example.salesnotes.data

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id_item") val id: Int,
    @SerializedName("category") val category: String,
    @SerializedName("name") val productName: String,
    @SerializedName("picture_url") val imgUrl: String,
    @SerializedName("price") val price: Number,
    @SerializedName("stock") val stock: Int,
    var isChecked: Boolean
    )
