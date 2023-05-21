package com.example.salesnotes.data

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id_item") val idItem: String,
    @SerializedName("category") val category: String,
    @SerializedName("name") val name: String,
    @SerializedName("picture_url") val pictureUrl: String,
    @SerializedName("price") val price: Double,
    @SerializedName("stock") val stock: Int
    )
