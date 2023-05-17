package com.example.salesnotes.data

import androidx.lifecycle.LiveData

data class Items(var productName : String,
                 var id : Int,
                 var price : Number,
                 var stock : Number,
                 var isChecked : Boolean,
                 var imgUrl : Int

)
