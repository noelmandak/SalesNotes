package com.example.salesnotes

import androidx.lifecycle.ViewModel
import com.example.salesnotes.data.Item

class SharedViewModel : ViewModel() {
    var token = ""
    var salesName = ""
    var salesId = ""
    var checkOutItem = mutableListOf<Item>()


}