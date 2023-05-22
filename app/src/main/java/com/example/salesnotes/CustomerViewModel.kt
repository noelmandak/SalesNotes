package com.example.salesnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesnotes.RetrofitInstance.customerService
import com.example.salesnotes.data.Customer
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerViewModel : ViewModel() {
    val customersLiveData: MutableLiveData<List<Customer>> = MutableLiveData()
    var currentCustomer: MutableLiveData<Customer> = MutableLiveData()
    var arrayCustomer = ArrayList<String>()
    fun getAllCustomers(token: String) {
        viewModelScope.launch {
            try {
                val customerList = customerService.getAllCustomers(token)
                customerList
                customersLiveData.value = customerList
                currentCustomer.value = customerList[0]
            } catch (e: Exception){
                // Tangani error
            }
        }



//        val apiService = RetrofitInstance.customerService
//        val call = apiService.getAllCustomers(token)

//        call.enqueue(object : Callback<List<Customer>> {
//            override fun onResponse(call: Call<List<Customer>>, response: Response<List<Customer>>) {
//                response
//                if (response.isSuccessful) {
//                    val customers = response.body()
//                    if (customers!=null){
//                        customersLiveData.value = customers
//                    }
//                } else {
//                    // Menghandle kesalahan respons
//                }
//            }
//
//            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
//                // Menghandle kegagalan permintaan
//            }
//        })
    }

    fun changeCustomerDetail(index:Int) {
        currentCustomer.value = customersLiveData.value?.get(index)
    }

    fun toArray() {
        val array = ArrayList<String>()
        for (customer in customersLiveData.value!!) {
            array.add(customer.name)
        }
        arrayCustomer = array
    }

    fun getIdCustomer(name:String): Int {
        for (customer in customersLiveData.value!!) {
            if (customer.name == name){
                return customer.id_customer
            }
        }
        return -1
    }

}