package com.example.salesnotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesnotes.data.Customer

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val customersLiveData: MutableLiveData<List<Customer>?> = MutableLiveData()
//    lateinit var customersLiveData : ArrayList<Customer>
//    init {
//        getAllCustomers("bm9lbA==")
//    }
    fun getAllCustomers(token: String) {
        val apiService = RetrofitInstance.apiService
        val call = apiService.getAllCustomers(token)

        call.enqueue(object : Callback<List<Customer>> {
            override fun onResponse(call: Call<List<Customer>>, response: Response<List<Customer>>) {
                response
                if (response.isSuccessful) {
                    val customers = response.body()
                    customers
                    customersLiveData.value = customers
                } else {
                    // Menghandle kesalahan respons
                }
            }

            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                // Menghandle kegagalan permintaan
            }
        })
    }
}