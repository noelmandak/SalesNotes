package com.example.salesnotes.data

import com.google.gson.annotations.SerializedName

data class ItemsCheckout(var productName : String,
                         var price : Number,
                         var stock : Number,
                         var qty : Number,
                         var imgUrl : Int)
data class CheckoutRequest(
    @SerializedName("customerId") val customerId: Int,
    @SerializedName("cart") val cart: List<CartItem>
)

data class CartItem(
    @SerializedName("id_item") val itemId: Int,
    @SerializedName("qty") val quantity: Int
)
data class OrderResponse(
    @SerializedName("status") val status: String,
    @SerializedName("id_transaction") val transactionId: String,
    @SerializedName("total") val total: Int
)