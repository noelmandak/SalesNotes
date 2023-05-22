package com.example.salesnotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesnotes.data.Item
import com.example.salesnotes.data.OrderResponse

class SharedViewModel : ViewModel() {
    var token = "cGF0cmljaw=="
    var salesName = "James Patrick"
    var salesId = "James Patrick"
    var checkOutItem = mutableListOf<Item>()
    var orderRespons : MutableLiveData<OrderResponse> = MutableLiveData()



}